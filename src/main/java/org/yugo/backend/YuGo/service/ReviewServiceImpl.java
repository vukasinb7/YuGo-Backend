package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;
import org.yugo.backend.YuGo.repository.RideReviewRepository;
import org.yugo.backend.YuGo.repository.VehicleReviewRepository;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RideReviewRepository rideReviewRepository;
    private final VehicleReviewRepository vehicleReviewRepository;

    @Autowired
    public ReviewServiceImpl(RideReviewRepository rideReviewRepository, VehicleReviewRepository vehicleReviewRepository) {
        this.rideReviewRepository = rideReviewRepository;
        this.vehicleReviewRepository = vehicleReviewRepository;
    }

    /* =========================== VehicleReview =========================== */
    @Override
    public VehicleReview addVehicleReview(VehicleReview vehicleReview){
        return vehicleReviewRepository.save(vehicleReview);
    }

    @Override
    public VehicleReview getVehicleReview(Integer id){
        return vehicleReviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<VehicleReview> getAllVehicleReviews(){
        return vehicleReviewRepository.findAll();
    }

    /* =========================== RideReview =========================== */
    @Override
    public RideReview addRideReview(RideReview rideReview){
        return rideReviewRepository.save(rideReview);
    }

    @Override
    public RideReview getRideReview(Integer id){
        return rideReviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<RideReview> getAllRideReviews(){
        return rideReviewRepository.findAll();
    }
}
