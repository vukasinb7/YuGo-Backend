package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.WorkTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    @Query(value = "SELECT * FROM RIDES r WHERE r.driver_id = :driver_id and r.status='ACTIVE'",
            nativeQuery = true)
    Ride findActiveRideByDriver(@Param("driver_id") Integer driverID);

    @Query(value = "SELECT * FROM RIDES r WHERE r.status='ACTIVE' and r.id in (SELECT ur.ride_id from PASSENGER_RIDES ur WHERE r.id=ur.ride_id and :passenger_id=ur.passenger_id)",
            nativeQuery = true)
    Ride findActiveRideByPassenger(@Param("passenger_id") Integer passengerID);

    @Query(value = "SELECT DISTINCT r FROM Ride r LEFT JOIN r.passengers p WHERE :passengerId = p.id AND r.startTime>=:fromTime AND :toTime>=r.endTime")
    Page<Ride> findRidesByPassenger(@Param("passengerId") Integer passengerId,
                                    @Param("fromTime") LocalDateTime fromTime,
                                    @Param("toTime") LocalDateTime toTime, Pageable page);

    @Query(value = "SELECT DISTINCT r FROM Ride r LEFT JOIN r.passengers p WHERE (:userId = p.id OR :userId = r.driver.id) AND r.startTime>=:fromTime AND :toTime>=r.endTime")
    Page<Ride> findRidesByUser(@Param("userId") Integer userId,
                                    @Param("fromTime") LocalDateTime fromTime,
                                    @Param("toTime") LocalDateTime toTime, Pageable page);

    @Query(value = "SELECT ride FROM Ride ride WHERE ride.driver.id = ?1 and ride.startTime >= ?2 and ride.endTime < ?3")
    Page<Ride> findRidesByDriverAndStartTimeAndEndTimePageable(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);
}