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
    @Query(value = "SELECT * FROM RIDES r WHERE r.driver_id = :did and r.status='ACTIVE'",
            nativeQuery = true)
    public Ride getActiveRideByDriver(@Param("did") Integer did);
    @Query(value = "SELECT * FROM RIDES r WHERE r.status='ACTIVE' and r.id in (SELECT ur.rides_id from USERS_RIDES ur WHERE r.id=ur.rides_id and :pid=passenger_id)",
            nativeQuery = true)
    public Ride getActiveRideByPassenger(@Param("pid") Integer did);

    @Query(value = "SELECT * FROM RIDES WHERE passenger_id = passengerID", nativeQuery = true)
    public Page<Ride> findRidesForPassenger(@Param("passengerID") Integer passengerID,
                                            @Param("fromTime") LocalDateTime fromTime,
                                            @Param("toTime") LocalDateTime toTime, Pageable page);
}