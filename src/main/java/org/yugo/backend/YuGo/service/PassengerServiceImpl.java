package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final UserRepository userRepository;

    @Autowired
    public PassengerServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllPassengers();
    }

    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }
}
