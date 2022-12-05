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

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    /*public ResponseEntity<RideDetailedOut> addRide(@RequestBody RideIn rideIn){
        Ride ride= new Ride(rideIn);
        //rideService.insert(ride);
        return new ResponseEntity<>(RideMapper.fromRidetoDTO(ride), HttpStatus.OK);
    }*/

    /*@GetMapping(
            value = "/active/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> getActiveRidesByDriver(@PathVariable Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.getActiveRideByDriver(id)), HttpStatus.OK);
    }*/

    @GetMapping(
            value = "/active/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> getActiveRidesByPassenger(@PathVariable Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.getActiveRideByPassenger(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> getRideById(@PathVariable Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.get(id).get()), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> cancelRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        if (ride.getStatus()==RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.CANCELED);
            rideService.insert(ride);
        }
        return new ResponseEntity<>(new RideDetailedOut(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/accept",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> acceptRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.ACCEPTED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideDetailedOut(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/end",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> endRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.FINISHED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideDetailedOut(ride), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/panic",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PanicSimplifiedOut> addPanic(@RequestBody ReasonIn reasonIn, @PathVariable Integer id){
        Ride ride= rideService.get(id).get();
        Panic panic= new Panic(new Passenger(),ride, LocalDateTime.now(), reasonIn.getReason());
        ride.setIsPanicPressed(true);
        rideService.insert(ride);
        panicService.insert(panic);

        return new ResponseEntity<>(new PanicSimplifiedOut(panic), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/cancel",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> rejectRide(@RequestBody ReasonIn reasonIn, @PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        ride.setStatus(RideStatus.REJECTED);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideDetailedOut(ride), HttpStatus.OK);

    }

}
