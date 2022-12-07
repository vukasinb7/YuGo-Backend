package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.repository.RideReviewRepository;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RideReviewRepository rideReviewRepository;

    @Autowired
    public ReviewServiceImpl(RideReviewRepository rideReviewRepository) {
        this.rideReviewRepository = rideReviewRepository;
    }

    @Override
    public RideReview insertRideReview(RideReview rideReview) {
        return rideReviewRepository.save(rideReview);
    }

    @Override
    public RideReview getRideReview(Integer id) {
        return rideReviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<RideReview> getAllRideReviews() {
        return rideReviewRepository.findAll();
    }

    @Override
    public List<RideReview> getRideReviewsByDriver(Integer id) {
        return rideReviewRepository.findReviewsByDriver(id);
    }

    @Override
    public List<RideReview> getRideReviewsByVehicle(Integer id) {
        return rideReviewRepository.findReviewsByVehicle(id);
    }

    @Override
    public List<RideReview> getDriverReviewsByRide(Integer id) {
        return rideReviewRepository.findDriverReviewsByRide(id);
    }

    @Override
    public List<RideReview> getVehicleReviewsByRide(Integer id) {
        return rideReviewRepository.findVehicleReviewsByRide(id);
    }
    @Override
    public RideReview getDriverReviewsByRideByPassenger(Integer id,Integer passengerId){return rideReviewRepository.findDriverReviewsByRideByPassenger(id,passengerId);}

    @Override
    public RideReview getVehicleReviewsByRideByPassenger(Integer id,Integer passengerId){return rideReviewRepository.findVehicleReviewsByRideByPassenger(id,passengerId);}
}