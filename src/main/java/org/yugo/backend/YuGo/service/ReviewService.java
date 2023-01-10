package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

public interface ReviewService {
    RideReview insertRideReview(RideReview rideReview);
    RideReview getRideReview(Integer id);
    List<RideReview> getAllRideReviews();
    List<RideReview> getRideReviewsByDriver(Integer id);
    List<RideReview> getRideReviewsByVehicle(Integer id);
    List<RideReview> getDriverReviewsByRide(Integer id);
    List<RideReview> getVehicleReviewsByRide(Integer id);
    RideReview getDriverReviewsByRideByPassenger(Integer id,Integer passengerId);
    RideReview getVehicleReviewsByRideByPassenger(Integer id,Integer passengerId);
}
