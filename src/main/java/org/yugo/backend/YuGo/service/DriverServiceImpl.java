package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.repository.UserRepository;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.WorkTimeRepository;

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
    public Driver insertDriver(Driver driver){
        try{
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleService.findRoleByName("ROLE_DRIVER"));
            driver.setRoles(roles);
            Vehicle vehicle = new Vehicle();
            driver.setVehicle(vehicle);
            driver.setPassword(passwordEncoder.encode(driver.getPassword()));
            return userRepository.save(driver);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException("Email is already being used by another user");
        }
    }

    @Override
    public List<User> getAllDrivers() {
        return userRepository.findAllDrivers();
    }

    @Override
    public Optional<Driver> getDriver(Integer id) {
        return userRepository.findDriverById(id);
    }

    @Override
    public WorkTime insertWorkTime(Integer driverId, WorkTime workTime){
        Optional<User> driverOpt = userRepository.findById(driverId);
        if(driverOpt.isEmpty() || driverOpt.get().getClass() != Driver.class){
            return null;
        }
        Driver driver = (Driver) driverOpt.get();
        workTime.setDriver(driver);
        return workTimeRepository.save(workTime);
    }

    @Override
    public List<WorkTime> getAllWorkTimes() {
        return workTimeRepository.findAll();
    }

    @Override
    public Optional<WorkTime> getWorkTime(Integer id) {
        return workTimeRepository.findById(id);
    }

    @Override
    public Driver updateDriver(Driver driverUpdate){
        Optional<User> driverOpt = userRepository.findById(driverUpdate.getId());
        if(driverOpt.isEmpty()){
            return null;
        }
        Driver driver = (Driver) driverOpt.get();
        driver.setName(driverUpdate.getName());
        driver.setSurname(driverUpdate.getSurname());
        driver.setProfilePicture(driverUpdate.getProfilePicture());
        driver.setTelephoneNumber(driverUpdate.getTelephoneNumber());
        driver.setEmail(driverUpdate.getEmail());
        driver.setAddress(driverUpdate.getAddress());
        return userRepository.save(driver);
    }

    @Override
    public Page<User> getDriversPage(Pageable page){
        return userRepository.findAllDrivers(page);
    }

    @Override
    public Page<WorkTime> getDriverWorkingTimesPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        return workTimeRepository.findWorkTimesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }

    @Override
    public WorkTime updateWorkTime(WorkTime workTime){
        Optional<WorkTime> workTimeOpt = workTimeRepository.findById(workTime.getId());
        if(workTimeOpt.isEmpty()){
            return null;
        }
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
