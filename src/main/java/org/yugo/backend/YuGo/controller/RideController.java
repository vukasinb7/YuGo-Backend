package org.yugo.backend.YuGo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.mapper.FavoritePathMapper;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> addRide(@RequestBody @Valid RideIn rideIn) throws Exception {
        Ride ride = rideService.createRide(rideIn);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime rideStart = LocalDateTime.parse(rideIn.getDateTime(), formatter);
        rideService.searchForDriver(ride, rideStart);
        return new ResponseEntity<>(RideMapper.fromRidetoDTO(ride), HttpStatus.OK);
    }

    @GetMapping(
            value = "/driver/{id}/active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<RideDetailedOut> getActiveRidesByDriver(@PathVariable @NotNull @Positive Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.getActiveRideByDriver(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/passenger/{id}/active",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> getActiveRidesByPassenger(@PathVariable @NotNull @Positive Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.getActiveRideByPassenger(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> getRideById(@PathVariable @NotNull @Positive Integer id){
        return new ResponseEntity<>(new RideDetailedOut(rideService.get(id)), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/withdraw",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> cancelRide(@PathVariable @NotNull @Positive Integer id){

        return new ResponseEntity<>(new RideDetailedOut(rideService.cancelRide(id)), HttpStatus.OK);
    }
    @PutMapping(
            value = "/{id}/start",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> startRide(@PathVariable @NotNull @Positive Integer id){

        return new ResponseEntity<>(new RideDetailedOut(rideService.startRide(id)), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/accept",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> acceptRide(@PathVariable @NotNull @Positive Integer id){

        return new ResponseEntity<>(new RideDetailedOut(rideService.acceptRide(id)), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/end",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<RideDetailedOut> endRide(@PathVariable @NotNull @Valid Integer id){

        return new ResponseEntity<>(new RideDetailedOut(rideService.endRide(id)), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/panic",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<PanicOut> addPanic(@RequestBody @Valid ReasonIn reasonIn, @PathVariable @NotNull @Positive Integer id){
        Ride ride= rideService.get(id);
        Panic panic= new Panic(passengerService.get(1),ride, LocalDateTime.now(), reasonIn.getReason());
        ride.setIsPanicPressed(true);
        rideService.insert(ride);
        panicService.insert(panic);

        return new ResponseEntity<>(new PanicOut(panic), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/cancel",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<RideDetailedOut> rejectRide(@RequestBody @Valid ReasonIn reasonIn, @PathVariable @NotNull @Positive Integer id){

        return new ResponseEntity<>(new RideDetailedOut(rideService.rejectRide(id,reasonIn.getReason())), HttpStatus.OK);

    }

    @PostMapping(
            value = "/favorites",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    public ResponseEntity<FavoritePathOut> addFavoritePath(@RequestBody @Valid FavoritePathIn favoritePathIn){
        Set<Passenger> passengers=new HashSet<>();
        for (UserSimplifiedOut user:favoritePathIn.getPassengers()) {
            passengers.add(passengerService.get(user.getId()));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (favoritePathService.getByPassengerId(user.getId()).size()>10)
            throw new BadRequestException("Number of favorite rides cannot exceed 10!");
        FavoritePath favoritePath= new FavoritePath(favoritePathIn.getFavoriteName(),favoritePathIn.getLocations().stream().map(PathMapper::fromDTOtoPath).collect(Collectors.toList()), passengers,vehicleService.getVehicleTypeByName(favoritePathIn.getVehicleType()),favoritePathIn.getBabyTransport(),favoritePathIn.getPetTransport());
        favoritePath.setOwner(passengerService.get(user.getId()));
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
        return new ResponseEntity<>(favoritePathService.getByPassengerId(user.getId()).stream()
                .map(FavoritePathMapper::fromFavoritePathtoDTO)
                .toList(), HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/favorites/{id}"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    ResponseEntity<Void> deleteFavoritePath(@PathVariable(name = "id") @NotNull @Positive Integer favoritePathId){
        favoritePathService.delete(favoritePathId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
