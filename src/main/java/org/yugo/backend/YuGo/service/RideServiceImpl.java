package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.repository.RideRepository;

import java.util.List;
import java.util.Optional;

public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository){
        this.rideRepository = rideRepository;
    }

    @Override
    public Ride add(Ride ride){
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
}
