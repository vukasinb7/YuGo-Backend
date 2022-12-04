package org.yugo.backend.YuGo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.ReviewRequest;
import org.yugo.backend.YuGo.dto.ReviewResponse;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.ReviewService;
import org.yugo.backend.YuGo.service.RideService;
import org.yugo.backend.YuGo.service.VehicleService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final VehicleService vehicleService;
    private final RideService rideService;

    @Autowired
    public ReviewController(ReviewService reviewService, VehicleService vehicleService,RideService rideService){
        this.reviewService=reviewService;
        this.vehicleService=vehicleService;
        this.rideService=rideService;
    }

    @PostMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewResponse> addVehicleReview(@RequestBody ReviewRequest reviewRequest, @PathVariable Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        VehicleReview vehicleReview= new VehicleReview(reviewRequest.getComment(),reviewRequest.getRating(),vehicle,null);
        reviewService.saveVehicleReview(vehicleReview);
        return new ResponseEntity<>(new ReviewResponse(vehicleReview), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewResponse> addRideReview(@RequestBody ReviewRequest reviewRequest, @PathVariable Integer id){
        Ride ride=rideService.get(id).get();
        RideReview rideReview = new RideReview(reviewRequest.getComment(),reviewRequest.getRating(),ride,null);
        reviewService.saveRideReview(rideReview);
        return new ResponseEntity<>(new ReviewResponse(rideReview), HttpStatus.OK);
    }




}
