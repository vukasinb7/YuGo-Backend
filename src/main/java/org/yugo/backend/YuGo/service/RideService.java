package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideService {
    List<Ride> getAll();

    Optional<Ride> get(Integer id);

    public Ride getActiveRideByDriver(Integer id);

    public Ride getActiveRideByPassenger(Integer id);

    public Ride insert(Ride ride);

    public Page<Ride> getPassengerRides(Integer passengerID, LocalDateTime from, LocalDateTime to, Pageable page);

    public Page<Ride> getUserRides(Integer userID, LocalDateTime from, LocalDateTime to, Pageable page);
}
