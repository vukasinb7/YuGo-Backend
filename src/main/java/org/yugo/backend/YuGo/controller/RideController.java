package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.PanicService;
import org.yugo.backend.YuGo.service.RideService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final RideService rideService;
    private final PanicService panicService;

    @Autowired
    public RideController(RideService rideService, PanicService panicService){
        this.rideService=rideService;
        this.panicService=panicService;
    }

    /*@GetMapping(
            value = "/active/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> getActiveRidesByDriver(@PathVariable Integer id){
        return new ResponseEntity<>(new RideResponse(rideService.getActiveRideByDriver(id)), HttpStatus.OK);
    }*/

    @GetMapping(
            value = "/active/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> getActiveRidesByPassenger(@PathVariable Integer id){
        return new ResponseEntity<>(new RideResponse(rideService.getActiveRideByPassenger(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> getRideById(@PathVariable Integer id){
        return new ResponseEntity<>(new RideResponse(rideService.get(id).get()), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> cancelRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        if (ride.getStatus()==RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.CANCELED);
            rideService.insert(ride);
        }
        return new ResponseEntity<>(new RideResponse(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/accept",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> acceptRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.ACCEPTED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideResponse(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/end",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> endRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.FINISHED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideResponse(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/panic",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PanicResponse> addPanic(@RequestBody ReasonRequest reasonRequest, @PathVariable Integer id){
        Ride ride= rideService.get(id).get();
        Panic panic= new Panic(new Passenger(),ride, LocalDateTime.now(),reasonRequest.getReason());
        ride.setIsPanicPressed(true);
        rideService.insert(ride);
        panicService.insert(panic);

        return new ResponseEntity<>(new PanicResponse(panic), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/cancel",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> rejectRide(@RequestBody ReasonRequest reasonRequest, @PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.REJECTED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideResponse(ride), HttpStatus.OK);

    }

}
