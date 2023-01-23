package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.dto.RideIn;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideService {
    Ride insert(Ride ride);
    Ride createRide(RideIn rideIn) throws Exception;
    Page<Ride> getRidesByDriverPage(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);

    List<Ride> getAllByDate(LocalDateTime from, LocalDateTime to);

    Ride get(Integer id);

    List<Ride> getAll();
    Ride getActiveRideByDriver(Integer id);
    Ride getActiveRideByPassenger(Integer id);
    Page<Ride> getPassengerRides(Integer passengerID, LocalDateTime from, LocalDateTime to, Pageable page);

    List<Ride> getPassengerRidesNonPagable(Integer passengerId, LocalDateTime from, LocalDateTime to);

    Page<Ride> getUserRides(Integer userID, LocalDateTime from, LocalDateTime to, Pageable page);

    List<Ride> getRidesByDriverNonPageable(Integer driverId, LocalDateTime start, LocalDateTime end);

    Ride cancelRide(Integer id);
    Ride startRide(Integer id);
    Ride acceptRide(Integer id);
    Ride endRide(Integer id);
    Ride rejectRide(Integer id,String reason);
    Ride save(Ride ride);
}
