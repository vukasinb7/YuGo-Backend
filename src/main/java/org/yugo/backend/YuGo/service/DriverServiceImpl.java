package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    @Autowired
    public DriverServiceImpl(UserRepository userRepository, WorkTimeRepository workTimeRepository, VehicleRepository vehicleRepository){
        this.userRepository = userRepository;
        this.workTimeRepository = workTimeRepository;
        this.vehicleRepository = vehicleRepository;
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
        return userRepository.save(driver);
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
