package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.repository.RideRepository;

import java.util.List;


@Component
public class ScheduledTasks {

    private final RideRepository rideRepository;
    private final RideService rideService;

    @Autowired
    public ScheduledTasks(RideRepository rideRepository, RideService rideService){
        this.rideRepository = rideRepository;
        this.rideService = rideService;
    }

    @Scheduled(fixedRate = 60000)
    public void scheduleRides() {
        List<Ride> ridesToSchedule = rideRepository.findScheduledRidesInNext30Minutes();
        for(Ride ride : ridesToSchedule){
            rideService.searchForDriver(ride.getId());
        }
    }
}
