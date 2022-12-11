package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.WorkTime;
import org.yugo.backend.YuGo.repository.UserRepository;
import org.yugo.backend.YuGo.repository.VehicleRepository;
import org.yugo.backend.YuGo.repository.WorkTimeRepository;

import java.time.LocalDateTime;
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
    public Driver updateDriver(Driver driver){
        Optional<User> driverOpt = userRepository.findById(driver.getId());
        if(driverOpt.isEmpty()){
            return null;
        }
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