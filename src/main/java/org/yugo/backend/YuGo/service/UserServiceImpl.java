package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.repository.UserActivationRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.time.LocalDateTime;
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
    public User getUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            return userOpt.get();
        }
        else{
            throw new NotFoundException("User does not exist!");
        }
    }

    @Override
    public User updateUser(User userUpdate){
        User user = getUser(userUpdate.getId());
        user.setName(userUpdate.getName());
        user.setSurname(userUpdate.getSurname());
        user.setProfilePicture(userUpdate.getProfilePicture());
        user.setTelephoneNumber(userUpdate.getTelephoneNumber());
        user.setEmail(userUpdate.getEmail());
        user.setAddress(userUpdate.getAddress());
        return userRepository.save(user);
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
    public void blockUser(Integer userId){
        User user = getUser(userId);
        if (user.isBlocked()){
            throw new BadRequestException("User already blocked!");
        }
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void unblockUser(Integer userId){
        User user = getUser(userId);
        if (!user.isBlocked()){
            throw new BadRequestException("User is not blocked!");
        }
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Override
    public void activateUser(Integer activationId){
        Optional<UserActivation> userActivationOpt = userActivationRepository.findById(activationId);
        if (userActivationOpt.isPresent()){
            UserActivation userActivation = userActivationOpt.get();

            if (userActivation.getDateCreated().plus(userActivation.getLifeSpan()).isBefore(LocalDateTime.now())){
                throw new BadRequestException("Activation expired. Register again!");
            }
            userActivation.getUser().setActive(true);
            userActivationRepository.save(userActivation);
        }
        else{
            throw new NotFoundException("Activation with entered id does not exist!");
        }
    }
}
