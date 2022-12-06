package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

public interface RideReviewRepository extends JpaRepository<RideReview,Integer> {

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='DRIVER' AND rr.ride in (SELECT r.id FROM RIDES r WHERE rr.ride=r.id AND r.driver_id=:driver_id)", nativeQuery = true)
    List<RideReview> findReviewsByDriver(@Param("driver_id") Integer driver_id);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='VEHICLE' AND rr.ride in (SELECT r.id FROM RIDES r WHERE rr.ride=r.id AND r.driver_id in (SELECT d.id from USERS d where r.driver_id=d.id and d.vehicle_id=:vehicle_id))", nativeQuery = true)
    List<RideReview> findReviewsByVehicle(@Param("vehicle_id") Integer vehicle_id);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='DRIVER' AND rr.ride=:ride_id", nativeQuery = true)
    List<RideReview> findDriverReviewsByRide(@Param("ride_id") Integer ride_id);

    @Query(value = "SELECT * FROM RIDE_REVIEWS rr WHERE rr.type='VEHICLE' AND rr.ride=:ride_id", nativeQuery = true)
    List<RideReview> findVehicleReviewsByRide(@Param("ride_id") Integer ride_id);
}
