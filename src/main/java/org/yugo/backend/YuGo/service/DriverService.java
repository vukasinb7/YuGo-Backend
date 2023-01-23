package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface DriverService {
    List<Driver> getDriversInRange(double latitude, double longitude, double rangeInMeters);
    Driver insertDriver(Driver driver);
    Driver updateDriver(Driver driverUpdate);
    Vehicle createDriverVehicle(Integer driverId, Vehicle vehicle);
    Vehicle updateDriverVehicle(Integer driverId, Vehicle updatedVehicle);
    List<User> getAllDrivers();
    Page<User> getDriversPage(Pageable page);
    Driver getDriver(Integer id);

    void updateDriverStatus(Integer driverID, boolean isOnline);

    Vehicle getDriverVehicle(Integer driverID);
    WorkTime insertWorkTime(Integer driverId, WorkTime workTime);
    List<WorkTime> getAllWorkTimes();
    WorkTime getWorkTime(Integer id);
    Page<WorkTime> getDriverWorkingTimesPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);
    WorkTime endWorkTime(Integer workingTimeID, LocalDateTime endTime);
}
