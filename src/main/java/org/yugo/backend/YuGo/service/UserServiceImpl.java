package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.repository.UserActivationRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserActivationRepository userActivationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserActivationRepository userActivationRepository){
        this.userRepository = userRepository;
        this.userActivationRepository = userActivationRepository;
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public UserActivation saveUserActivation(UserActivation userActivation){
        return userActivationRepository.save(userActivation);
    }

    @Override
    public List<UserActivation> getAllUserActivations() {
        return userActivationRepository.findAll();
    }

    @Override
    public Optional<UserActivation> getUserActivation(Integer id) {
        return userActivationRepository.findById(id);
    }
}
