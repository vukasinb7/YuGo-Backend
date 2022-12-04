package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

import java.time.LocalDateTime;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    @Query(value = "SELECT * FROM RIDES r WHERE r.driver_id = :driver_id and r.status='ACTIVE'",
            nativeQuery = true)
    Ride getActiveRideByDriver(@Param("driver_id") Integer driverID);


    //ne radi
    @Query(value = "SELECT * FROM RIDES WHERE passenger_id = passengerID AND startTime>=fromTime AND toTime>=endTime", nativeQuery = true)
    Page<Ride> findRidesForPassenger(Integer passengerID, LocalDateTime fromTime, LocalDateTime toTime, Pageable page);
    
    @Query(value = "SELECT * FROM RIDES r WHERE r.status='ACTIVE' and r.id in (SELECT ur.rides_id from USERS_RIDES ur WHERE r.id=ur.rides_id and :passenger_id=ur.passenger_id)",
            nativeQuery = true)
    Ride getActiveRideByPassenger(@Param("passenger_id") Integer passengerID);

}
