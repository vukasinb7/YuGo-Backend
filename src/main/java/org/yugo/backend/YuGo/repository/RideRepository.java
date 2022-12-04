package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDateTime;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    @Query(value = "SELECT * FROM RIDES r WHERE r.driver_id = :driver_id and r.status='ACTIVE'",
            nativeQuery = true)
    Ride findActiveRideByDriver(@Param("driver_id") Integer driverID);

    @Query(value = "SELECT * FROM RIDES r WHERE r.status='ACTIVE' and r.id in (SELECT ur.rides_id from USERS_RIDES ur WHERE r.id=ur.rides_id and :passenger_id=ur.passenger_id)",
            nativeQuery = true)
    Ride findActiveRideByPassenger(@Param("passenger_id") Integer passengerID);

    @Query(value = "SELECT * FROM RIDES r WHERE r.id in (SELECT ur.rides_id from USERS_RIDES ur WHERE " +
            "r.id=ur.rides_id AND passenger_id = :passengerID) AND (r.start_time>=:fromTime OR :toTime>=r.end_time)", nativeQuery = true)
    public Page<Ride> findRidesByPassenger(@Param("passengerID") Integer passengerID,
                                           @Param("fromTime") LocalDateTime fromTime,
                                           @Param("toTime") LocalDateTime toTime, Pageable page);

    @Query(value = "SELECT * FROM RIDES r WHERE ((r.id in (SELECT ur.rides_id from USERS_RIDES ur WHERE " +
            "r.id=ur.rides_id AND passenger_id = :userID)) OR r.driver_id = :userID) AND (r.start_time>=:fromTime OR :toTime>=r.end_time)",
            nativeQuery = true)
    public Page<Ride> findRidesByUser(@Param("userID") Integer userID,
                                      @Param("fromTime") LocalDateTime fromTime,
                                      @Param("toTime") LocalDateTime toTime, Pageable page);
}