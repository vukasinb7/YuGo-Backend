package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.WorkTime;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver addDriver(Driver driver);
    Driver updateDriver(Driver driver);

    List<User> getAllDrivers();
    Page<User> getDriversPage(Pageable page);

    Optional<User> getDriver(Integer id);

    WorkTime addWorkTime(WorkTime workTime);

    List<WorkTime> getAllWorkTimes();

    Optional<WorkTime> getWorkTime(Integer id);
}
