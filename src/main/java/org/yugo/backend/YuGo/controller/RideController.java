package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.mapper.UserSimplifiedMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.PanicService;
import org.yugo.backend.YuGo.service.PassengerService;
import org.yugo.backend.YuGo.service.RideService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final RideService rideService;
    private final PanicService panicService;
    private final PassengerService passengerService;

    private final DriverService driverService;

    @Autowired
    public RideController(RideService rideService, PanicService panicService, PassengerService passengerService,DriverService driverService){
        this.rideService=rideService;
        this.panicService=panicService;
        this.passengerService=passengerService;
        this.driverService=driverService;
    }

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> addRide(@RequestBody RideIn rideIn){
        Set<Passenger> passengers=new HashSet<>();
        for (UserSimplifiedOut user:rideIn.getPassengers()) {
            passengers.add(passengerService.get(user.getId()).get());
        }
        Driver driver= (Driver) driverService.getDriver(2).get();
        Ride ride= new Ride(LocalDateTime.now(),LocalDateTime.now(),100,driver,passengers,rideIn.getLocations().stream().map(PathMapper::fromDTOtoPath).toList(),10,new HashSet<>(),new HashSet<>(),RideStatus.PENDING,null,false,rideIn.isBabyTransport(),rideIn.isPetTransport(),null);
        rideService.insert(ride);
        return new ResponseEntity<>(RideMapper.fromRidetoDTO(ride), HttpStatus.OK);
    }

    @GetMapping(
            value = "/driver/{id}/active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> getActiveRidesByDriver(@PathVariable Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.getActiveRideByDriver(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/passenger/{id}/active",
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
        if (ride.getStatus()!=RideStatus.ACTIVE) {
            ride.setStatus(RideStatus.CANCELED);
            rideService.insert(ride);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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
    public ResponseEntity<PanicOut> addPanic(@RequestBody ReasonIn reasonIn, @PathVariable Integer id){
        Ride ride= rideService.get(id).get();
        Panic panic= new Panic(passengerService.get(1).get(),ride, LocalDateTime.now(), reasonIn.getReason());
        ride.setIsPanicPressed(true);
        rideService.insert(ride);
        panicService.insert(panic);

        return new ResponseEntity<>(new PanicOut(panic), HttpStatus.OK);
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
