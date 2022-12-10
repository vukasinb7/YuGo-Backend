package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideService {
    Ride insert(Ride ride);
    Optional<Ride> get(Integer id);

    public Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);
    List<Ride> getAll();
    Ride getActiveRideByDriver(Integer id);
    Ride getActiveRideByPassenger(Integer id);
    Page<Ride> getPassengerRides(Integer passengerID, LocalDate from, LocalDate to, Pageable page);
    Page<Ride> getUserRides(Integer userID, LocalDate from, LocalDate to, Pageable page);
}
