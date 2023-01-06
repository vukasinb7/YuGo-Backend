package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.FavoritePathMapper;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.*;

import java.time.LocalDateTime;
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
    private final VehicleService vehicleService;
    private final FavoritePathService favoritePathService;
    private final RoutingService routingService;

    @Autowired
    public RideController(RideService rideService, PanicService panicService, PassengerService passengerService, DriverService driverService, VehicleService vehicleService, FavoritePathService favoritePathService, RoutingService routingService){
        this.rideService=rideService;
        this.panicService=panicService;
        this.passengerService=passengerService;
        this.driverService=driverService;
        this.vehicleService=vehicleService;
        this.favoritePathService=favoritePathService;
        this.routingService = routingService;
    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> addRide(@RequestBody RideIn rideIn){
//        Set<Passenger> passengers=new HashSet<>();
//        for (UserSimplifiedOut user:rideIn.getPassengers()) {
//            passengers.add(passengerService.get(user.getId()).get());
//        }
//        Driver driver= (Driver) driverService.getDriver(2).get();
//        Ride ride= new Ride(LocalDateTime.now(),LocalDateTime.now(),100,driver,passengers,rideIn.getLocations().stream().map(PathMapper::fromDTOtoPath).toList(),10,new HashSet<>(),RideStatus.PENDING,null,false,rideIn.isBabyTransport(),rideIn.isPetTransport(),null);
//        rideService.insert(ride);
        System.out.println("asffffffffffffffffffffffff");
        double fromLat = rideIn.getLocations().get(0).getDeparture().getLatitude();
        double fromLng = rideIn.getLocations().get(0).getDeparture().getLongitude();
        double toLat = rideIn.getLocations().get(0).getDestination().getLatitude();
        double toLng = rideIn.getLocations().get(0).getDestination().getLongitude();;

        RouteProperties routeProperties = routingService.getRouteProperties(fromLat, fromLng, toLat, toLng);
        System.out.println(routeProperties);
        //return new ResponseEntity<>(RideMapper.fromRidetoDTO(ride), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.OK);
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
            value = "/{id}/withdraw",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideDetailedOut> cancelRide(@PathVariable Integer id){
        Ride ride =rideService.get(id).get();
        if (ride.getStatus()!=RideStatus.ACTIVE) {
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
        Rejection rejection =new Rejection(ride,passengerService.get(1).get(),reasonIn.getReason(),LocalDateTime.now());
        ride.setRejection(rejection);
        rideService.insert(ride);
        return new ResponseEntity<>(new RideDetailedOut(ride), HttpStatus.OK);

    }

    @PostMapping(
            value = "/favorites",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    public ResponseEntity<FavoritePathOut> addFavoritePath(@RequestBody FavoritePathIn favoritePathIn){
        Set<Passenger> passengers=new HashSet<>();
        for (UserSimplifiedOut user:favoritePathIn.getPassengers()) {
            passengers.add(passengerService.get(user.getId()).get());
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        FavoritePath favoritePath= new FavoritePath(favoritePathIn.getFavoriteName(),favoritePathIn.getLocations().stream().map(PathMapper::fromDTOtoPath).collect(Collectors.toList()), passengers,vehicleService.getVehicleTypeByName(favoritePathIn.getVehicleType()),favoritePathIn.getBabyTransport(),favoritePathIn.getPetTransport());
        favoritePath.setOwner(passengerService.get(user.getId()).get());
        favoritePathService.insert(favoritePath);
        return new ResponseEntity<>(FavoritePathMapper.fromFavoritePathtoDTO(favoritePath), HttpStatus.OK);
    }

    @GetMapping(
            value = "/favorites",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    public ResponseEntity<List<FavoritePathOut>> getFavoritePathByPassengerId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return new ResponseEntity<>(favoritePathService.getByPassengerId(user.getId()).get().stream()
                .map(FavoritePathMapper::fromFavoritePathtoDTO)
                .toList(), HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/favorites/{id}"
    )
    @PreAuthorize("hasRole('PASSENGER')")
    ResponseEntity<Void> deleteDocument(@PathVariable(name = "id") Integer documentId){
        favoritePathService.delete(documentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
