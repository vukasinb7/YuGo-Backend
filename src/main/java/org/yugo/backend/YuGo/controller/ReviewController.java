package org.yugo.backend.YuGo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AllRideReviewsOut;
import org.yugo.backend.YuGo.dto.AllVehicleReviewsOut;
import org.yugo.backend.YuGo.dto.ReviewIn;
import org.yugo.backend.YuGo.dto.ReviewOut;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.ReviewService;
import org.yugo.backend.YuGo.service.RideService;
import org.yugo.backend.YuGo.service.VehicleService;

import java.util.List;
import java.util.stream.Stream;

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
            value = "/vehicle/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewOut> addVehicleReview(@RequestBody ReviewIn reviewIn, @PathVariable Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        VehicleReview vehicleReview= new VehicleReview(reviewIn.getComment(), reviewIn.getRating(),vehicle,null);
        reviewService.insertVehicleReview(vehicleReview);
        return new ResponseEntity<>(new ReviewOut(vehicleReview), HttpStatus.OK);
    }


    @PostMapping(
            value = "/driver/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewOut> addRideReview(@RequestBody ReviewIn reviewIn, @PathVariable Integer id){
        Ride ride=rideService.get(id).get();
        RideReview rideReview = new RideReview(reviewIn.getComment(),reviewIn.getRating(),ride,null);
        reviewService.insertRideReview(rideReview);
        return new ResponseEntity<>(new ReviewOut(rideReview), HttpStatus.OK);
    }

    @GetMapping(
            value = "vehicle/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllVehicleReviewsOut> getAllVehicleReviews(@RequestParam int id){
        List<VehicleReview> vehicleReviews=reviewService.getVehicleReviewsByVehicle(id);
        return new ResponseEntity<>(new AllVehicleReviewsOut((Stream<VehicleReview>) vehicleReviews), HttpStatus.OK);
    }

    @GetMapping(
            value = "driver/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllRideReviewsOut> getAllRideReviews(@RequestParam int id){
        List<RideReview> rideReviews =reviewService.getRideReviewsByDriver(id);
        return new ResponseEntity<>(new AllRideReviewsOut((Stream<RideReview>) rideReviews), HttpStatus.OK);
    }





}
