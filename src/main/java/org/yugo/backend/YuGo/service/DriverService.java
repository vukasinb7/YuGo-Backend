package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.WorkTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver insertDriver(Driver driver);
    Driver updateDriver(Driver driverUpdate);

    List<User> getAllDrivers();
    Page<User> getDriversPage(Pageable page);

    Optional<Driver> getDriver(Integer id);

    WorkTime insertWorkTime(Integer driverId, WorkTime workTime);

    List<WorkTime> getAllWorkTimes();

    Optional<WorkTime> getWorkTime(Integer id);

    Page<WorkTime> getDriverWorkingTimesPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);
    WorkTime updateWorkTime(WorkTime workTime);
    Vehicle changeVehicle(Driver driver, Vehicle vehicle);
}
