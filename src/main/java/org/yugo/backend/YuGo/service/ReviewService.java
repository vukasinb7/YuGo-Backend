package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;

public interface ReviewService {
    /* =========================== VehicleReview =========================== */
    VehicleReview addVehicleReview(VehicleReview vehicleReview);

    VehicleReview getVehicleReview(Integer id);

    List<VehicleReview> getAllVehicleReviews();

    /* =========================== RideReview =========================== */
    RideReview addRideReview(RideReview rideReview);

    RideReview getRideReview(Integer id);

    List<RideReview> getAllRideReviews();
}
