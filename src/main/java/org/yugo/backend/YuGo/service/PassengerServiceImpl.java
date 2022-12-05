package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.RideRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final UserRepository userRepository;
    private final RideRepository rideRepository;

    @Autowired
    public PassengerServiceImpl(UserRepository userRepository, RideRepository rideRepository){
        this.userRepository = userRepository;
        this.rideRepository = rideRepository;
    }

    @Override
    public User insert(User user){
        return userRepository.save(user);
    }

    @Override
    public User update(Integer userId, User updatedUser){
        Optional<User> userOpt = get(userId);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setProfilePicture(updatedUser.getProfilePicture());
            user.setTelephoneNumber(updatedUser.getTelephoneNumber());
            user.setEmail(updatedUser.getEmail());
            user.setAddress(updatedUser.getAddress());
            user.setPassword(updatedUser.getPassword());
            return  userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllPassengers();
    }

    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> getPassengersPage(Pageable page){
        return userRepository.findAllPassengers(page);
    }
}
