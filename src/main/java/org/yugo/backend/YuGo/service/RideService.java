package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideService {
    Ride insert(Ride ride);
    Optional<Ride> get(Integer id);
    List<Ride> getAll();
    Ride getActiveRideByDriver(Integer id);
    Ride getActiveRideByPassenger(Integer id);
    Page<Ride> getPassengerRides(Integer passengerID, LocalDateTime from, LocalDateTime to, Pageable page);
    Page<Ride> getUserRides(Integer userID, LocalDateTime from, LocalDateTime to, Pageable page);
}
