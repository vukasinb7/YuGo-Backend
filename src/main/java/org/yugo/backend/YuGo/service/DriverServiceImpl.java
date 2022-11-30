package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.WorkTime;
import org.yugo.backend.YuGo.repository.UserRepository;
import org.yugo.backend.YuGo.repository.WorkTimeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final UserRepository userRepository;
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public DriverServiceImpl(UserRepository userRepository, WorkTimeRepository workTimeRepository){
        this.userRepository = userRepository;
        this.workTimeRepository = workTimeRepository;
    }

    @Override
    public Driver addDriver(Driver driver){
        return userRepository.save(driver);
    }

    @Override
    public List<User> getAllDrivers() {
        return userRepository.findAllDrivers();
    }

    @Override
    public Optional<User> getDriver(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public WorkTime addWorkTime(WorkTime workTime){
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
}
