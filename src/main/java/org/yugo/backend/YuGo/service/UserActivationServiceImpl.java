package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.repository.UserActivationRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private final UserActivationRepository userActivationRepository;
    private final UserRepository userRepository;
    @Autowired
    public UserActivationServiceImpl(UserActivationRepository userActivationRepository, UserRepository userRepository){
        this.userActivationRepository = userActivationRepository;
        this.userRepository = userRepository;
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
    public UserActivation getUserActivation(Integer id) {
        Optional<UserActivation> userActivationOptional = userActivationRepository.findById(id);
        if (userActivationOptional.isPresent()){
            return userActivationOptional.get();
        }
        throw new NotFoundException("User activation not found!");
    }

    @Override
    public void activateUser(Integer activationId){
        Optional<UserActivation> userActivationOpt = userActivationRepository.findById(activationId);
        if (userActivationOpt.isPresent()){
            UserActivation userActivation = userActivationOpt.get();
            User user = userActivation.getUser();

            if (userActivation.getDateCreated().plus(userActivation.getLifeSpan()).isAfter(LocalDateTime.now())){
                user.setActive(true);
                userRepository.save(user);
                userActivation.setValid(false);
                userActivationRepository.save(userActivation);
                return;
            }
            userActivationRepository.delete(userActivation);
            userRepository.delete(user);
            throw new BadRequestException("Activation expired. Register again!");
        }
        else{
            throw new NotFoundException("Activation with entered id does not exist!");
        }
    }
}
