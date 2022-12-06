package org.yugo.backend.YuGo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AcumulatedReviewsOut;
import org.yugo.backend.YuGo.dto.AllRideReviewsOut;
import org.yugo.backend.YuGo.dto.ReviewIn;
import org.yugo.backend.YuGo.dto.ReviewOut;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.PassengerService;
import org.yugo.backend.YuGo.service.ReviewService;
import org.yugo.backend.YuGo.service.RideService;
import org.yugo.backend.YuGo.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final VehicleService vehicleService;
    private final RideService rideService;

    private final PassengerService passengerService;

    @Autowired
    public ReviewController(ReviewService reviewService, VehicleService vehicleService,RideService rideService, PassengerService passengerService){
        this.reviewService=reviewService;
        this.vehicleService=vehicleService;
        this.rideService=rideService;
        this.passengerService=passengerService;
    }

    @PostMapping(
            value = "/{rideId}/vehicle/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewOut> addVehicleReview(@RequestBody ReviewIn reviewIn,@PathVariable Integer rideId, @PathVariable Integer id){
        RideReview vehicleReview= new RideReview(reviewIn.getComment(), reviewIn.getRating(),rideService.get(rideId).get(),passengerService.get(1).get(),ReviewType.VEHICLE);
        reviewService.insertRideReview(vehicleReview);
        return new ResponseEntity<>(new ReviewOut(vehicleReview), HttpStatus.OK);
    }


    @PostMapping(
            value = "/{rideId}/driver/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReviewOut> addRideReview(@RequestBody ReviewIn reviewIn, @PathVariable Integer rideId, @PathVariable Integer id){
        RideReview rideReview= new RideReview(reviewIn.getComment(), reviewIn.getRating(),rideService.get(rideId).get(),passengerService.get(1).get(),ReviewType.DRIVER);
        reviewService.insertRideReview(rideReview);
        return new ResponseEntity<>(new ReviewOut(rideReview), HttpStatus.OK);
    }

    @GetMapping(
            value = "/vehicle/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllRideReviewsOut> getAllVehicleReviewsByVehicle(@PathVariable int id) {
        List<RideReview> vehicleReviews = reviewService.getRideReviewsByVehicle(id);
        return new ResponseEntity<>(new AllRideReviewsOut(vehicleReviews), HttpStatus.OK);
    }
    @GetMapping(
            value = "driver/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllRideReviewsOut> getAllRideReviewsByDriver(@PathVariable int id){
        List<RideReview> driverReviews = reviewService.getRideReviewsByDriver(id);
        return new ResponseEntity<>(new AllRideReviewsOut(driverReviews), HttpStatus.OK);
    }

    @GetMapping(
            value = "ride/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AcumulatedReviewsOut> getAllRideReviews(@PathVariable int id){
        List<RideReview> vehicleReviews = reviewService.getVehicleReviewsByRide(id);
        List<RideReview> driverReviews = reviewService.getDriverReviewsByRide(id);
        return new ResponseEntity<>(new AcumulatedReviewsOut(vehicleReviews,driverReviews), HttpStatus.OK);
    }





}
