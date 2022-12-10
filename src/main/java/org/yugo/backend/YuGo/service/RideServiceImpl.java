package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.repository.RideRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository){
        this.rideRepository = rideRepository;
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

    public Page<Ride> getPassengerRides(Integer passengerID, LocalDate from, LocalDate to, Pageable page){
        return rideRepository.findRidesByPassenger(passengerID, from, to, page);
    }

    public Page<Ride> getUserRides(Integer userID, LocalDate from, LocalDate to, Pageable page){
        return rideRepository.findRidesByUser(userID, from, to, page);
    }

    public Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end){
        return rideRepository.findRidesByDriverAndStartTimeAndEndTimePageable(driverId, page, start, end);
    }
}
