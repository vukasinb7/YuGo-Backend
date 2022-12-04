package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public User insertUser(User user){
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
    public UserActivation insertUserActivation(UserActivation userActivation){
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

    @Override
    public Page<User> getUsersPage(Pageable page){
        return userRepository.findAllUsers(page);
    }

    @Override
    public void authenticateUser(String email, String password){
        userRepository.authenticateUser(email, password);
    }

    @Override
    public boolean blockUser(Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            user.get().setBlocked(true);
            userRepository.save( user.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean unblockUser(Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            user.get().setBlocked(false);
            userRepository.save( user.get());
            return true;
        }
        return false;
    }
}
