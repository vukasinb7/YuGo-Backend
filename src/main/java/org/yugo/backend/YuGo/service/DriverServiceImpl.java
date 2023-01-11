package org.yugo.backend.YuGo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final UserRepository userRepository;
    private final WorkTimeRepository workTimeRepository;
    private final VehicleRepository vehicleRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DriverServiceImpl(UserRepository userRepository, WorkTimeRepository workTimeRepository,
                             VehicleRepository vehicleRepository, RoleService roleService,
                             BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.workTimeRepository = workTimeRepository;
        this.vehicleRepository = vehicleRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Driver> getDriversInRange(double latitude, double longitude, double rangeInMeters) {
        List<User> drivers = this.userRepository.findAllDrivers();
        List<Driver> output = new ArrayList<>();
        for(User user: drivers){
            Driver driver = (Driver) user;
            if(isInRange(driver, latitude, longitude, rangeInMeters)){
                output.add(driver);
            }
        }
        return output;
    }


    private boolean isInRange(Driver driver, double latitude, double longitude, double rangeInMeters){
        final double R = 6371000;     // mean radius of earth in meters
        double lat1 = driver.getVehicle().getCurrentLocation().getLatitude();
        double lon1 = driver.getVehicle().getCurrentLocation().getLongitude();
        double lat2 = latitude;
        double lon2 = longitude;

        double phi1 = lat1 * Math.PI / 180;
        double phi2 = lat2 * Math.PI / 180;
        double deltaPhi = (lat2 - lat1) * Math.PI / 180;
        double deltaLambda = (lon2 - lon1) * Math.PI / 180;

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                    Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d <= rangeInMeters;
    }

    @Override
    public Driver insertDriver(Driver driver){
        try{
            driver.setActive(true);
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleService.findRoleByName("ROLE_DRIVER"));
            driver.setRoles(roles);
            driver.setPassword(passwordEncoder.encode(driver.getPassword()));
            return userRepository.save(driver);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException("Email is already being used by another user!");
        }
    }

    @Override
    public List<User> getAllDrivers() {
        return userRepository.findAllDrivers();
    }

    @Override
    public Driver getDriver(Integer id) {
        Optional<Driver> driver = userRepository.findDriverById(id);
        if(driver.isEmpty()){
            throw new NotFoundException("Driver does not exist!");
        }
        return driver.get();
    }

    @Override
    public Vehicle getDriverVehicle(Integer driverID){
        Driver driver = getDriver(driverID);
        Vehicle vehicle = driver.getVehicle();
        if(vehicle == null){
            throw new BadRequestException("Vehicle is not assigned");
        }
        return vehicle;
    }
    @Override
    public WorkTime insertWorkTime(Integer driverId, WorkTime workTime){
        Driver driver = getDriver(driverId);
        if(unproxy(driver.getVehicle()) == null){
            throw new BadRequestException("Vehicle is not defined");
        }
        if(workTimeRepository.findWorkTimeByEndTimeIsNull().isPresent()){
            throw new BadRequestException("Shift already ongoing");
        }
        double totalWorkTime = workTimeRepository.getTotalWorkTimeInLast24Hours(driverId);
        if(totalWorkTime >= 8){
            throw new BadRequestException("Work time limit exceeded");
        }
        workTime.setDriver(driver);
        workTime.setEndTime(null);

        return workTimeRepository.save(workTime);
    }
    private <T> T unproxy(T object){
        return (T) Hibernate.unproxy(object);
    }
    @Override
    public List<WorkTime> getAllWorkTimes() {
        return workTimeRepository.findAll();
    }

    @Override
    public WorkTime getWorkTime(Integer id) {
        Optional<WorkTime> workTime = workTimeRepository.findById(id);
        if(workTime.isEmpty()){
            throw new NotFoundException("Working time does not exist");
        }
        return workTime.get();
    }

    @Override
    public Driver updateDriver(Driver driverUpdate){
        Driver driver = getDriver(driverUpdate.getId());
        if(!driver.getTelephoneNumber().matches("^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")){
            throw new BadRequestException("Invalid phone number");
        }
        if(!driver.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")){
            throw new BadRequestException("Invalid email address");
        }
        driver.setName(driverUpdate.getName());
        driver.setSurname(driverUpdate.getSurname());
        driver.setProfilePicture(driverUpdate.getProfilePicture());
        driver.setTelephoneNumber(driverUpdate.getTelephoneNumber());
        driver.setEmail(driverUpdate.getEmail());
        driver.setAddress(driverUpdate.getAddress());
        return userRepository.save(driver);
    }

    @Override
    public Driver updateDriverVehicle(Integer driverId, Vehicle vehicle){
        Optional<User> driverOpt = userRepository.findById(driverId);
        if(driverOpt.isEmpty()){
            return null;
        }
        Driver driver = (Driver) driverOpt.get();
        driver.setVehicle(vehicle);
        vehicle.setDriver(driver);
        vehicleRepository.save(vehicle);
        return userRepository.save(driver);
    }

    @Override
    public Page<User> getDriversPage(Pageable page){
        return userRepository.findAllDrivers(page);
    }

    @Override
    public Page<WorkTime> getDriverWorkingTimesPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        getDriver(driverId);
        return workTimeRepository.findWorkTimesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }

    @Override
    public WorkTime endWorkTime(Integer workingTimeID, LocalDateTime endTime){
        Optional<WorkTime> workTimeOpt = workTimeRepository.findById(workingTimeID);
        if(workTimeOpt.isEmpty()){
            throw new NotFoundException("Working time not found");
        }
        if(workTimeOpt.get().getEndTime() != null){
            throw new BadRequestException("No shift is ongoing");
        }
        WorkTime workTime = workTimeOpt.get();
        workTime.setEndTime(endTime);
        return workTimeRepository.save(workTime);
    }

    @Override
    public Vehicle changeVehicle(Driver driver, Vehicle vehicle){

        Vehicle vehicleCurrent = driver.getVehicle();

        if(vehicleCurrent != null){
            vehicle.setId(vehicle.getId());
        }

        vehicle.setDriver(driver);
        Vehicle output = vehicleRepository.save(vehicle);

        driver.setVehicle(vehicle);
        userRepository.save(driver);

        return output;
    }
}
