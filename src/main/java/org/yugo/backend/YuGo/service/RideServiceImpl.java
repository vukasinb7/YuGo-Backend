package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.repository.RideRepository;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final UserService userService;
    private final PassengerService passengerService;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, UserService userService, PassengerService passengerService){
        this.rideRepository = rideRepository;
        this.userService = userService;
        this.passengerService = passengerService;
    }

    @Override
    public Ride insert(Ride ride){
        return rideRepository.save(ride);
    }

    @Override
    public List<Ride> getAll() {
        return rideRepository.findAll();
    }

    @Override
    public Optional<Ride> get(Integer id) {
        return rideRepository.findById(id);
    }

    @Override
    public Ride getActiveRideByDriver(Integer id){ return rideRepository.findActiveRideByDriver(id);}

    @Override
    public Ride getActiveRideByPassenger(Integer id){ return rideRepository.findActiveRideByPassenger(id);}

    public Page<Ride> getPassengerRides(Integer passengerId, LocalDateTime from, LocalDateTime to, Pageable page){
        passengerService.get(passengerId);
        return rideRepository.findRidesByPassenger(passengerId, from, to, page);
    }

    public Page<Ride> getUserRides(Integer userId, LocalDateTime from, LocalDateTime to, Pageable page){
        userService.getUser(userId);
        return rideRepository.findRidesByUser(userId, from, to, page);
    }

    public Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        return rideRepository.findRidesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }
}
