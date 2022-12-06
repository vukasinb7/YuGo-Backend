package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

public interface RideReviewRepository extends JpaRepository<RideReview,Integer> {

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='DRIVER' AND rr.ride in (SELECT r.id FROM RIDES r WHERE rr.ride=r.id AND r.driver_id=:driver_id)", nativeQuery = true)
    List<RideReview> findReviewsByDriver(@Param("driver_id") Integer driverId);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='VEHICLE' AND rr.ride in (SELECT r.id FROM RIDES r WHERE rr.ride=r.id AND r.driver_id in (SELECT d.id from USERS d where r.driver_id=d.id and d.vehicle_id=:vehicle_id))", nativeQuery = true)
    List<RideReview> findReviewsByVehicle(@Param("vehicle_id") Integer vehicleId);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='DRIVER' AND rr.ride=:ride_id", nativeQuery = true)
    List<RideReview> findDriverReviewsByRide(@Param("ride_id") Integer rideId);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='VEHICLE' AND rr.ride=:ride_id", nativeQuery = true)
    List<RideReview> findVehicleReviewsByRide(@Param("ride_id") Integer rideId);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='DRIVER' AND rr.ride=:ride_id AND :ride_id in(SELECT  pr.ride_id FROM PASSENGER_RIDES pr WHERE pr.ride_id=:ride_id AND pr.passenger_id=:passenger_id )", nativeQuery = true)
    RideReview findDriverReviewsByRideByPassenger(@Param("ride_id") Integer rideId,@Param("passenger_id") Integer passengerId);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='VEHICLE' AND rr.ride=:ride_id AND :ride_id in (SELECT pr.ride_id FROM PASSENGER_RIDES pr WHERE pr.ride_id=:ride_id AND pr.passenger_id=:passenger_id )", nativeQuery = true)
    RideReview findVehicleReviewsByRideByPassenger(@Param("ride_id") Integer rideId,@Param("passenger_id") Integer passengerId);
}
