package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.service.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerService passengerService;
    private final UserService userService;
    private final RideService rideService;

    @Autowired
    public PassengerController(PassengerService passengerService, UserService userService, RideService rideService){
        this.passengerService = passengerService;
        this.userService = userService;
        this.rideService = rideService;
    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> addPassenger(@RequestBody UserDetailedIn user){
        Passenger newPass = new Passenger(user);
        passengerService.insert(newPass);
        UserActivation newAct = new UserActivation(newPass, LocalDateTime.now(), Duration.ZERO);
        userService.insertUserActivation(newAct);
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(newPass), HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersOut> getAllPassengers(@RequestParam int page, @RequestParam int size){
        Page<User> passengers = passengerService.getPassengersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(passengers), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{activationId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity activatePassenger(@PathVariable Integer activationId){
        if (userService.activateUser(activationId)){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> getPassenger(@PathVariable Integer id){
        Optional<User> userOpt = passengerService.get(id);
        if (userOpt.isPresent()){
            return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(userOpt.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> updatePassenger(@RequestBody UserDetailedIn updatedUserDTO, @PathVariable Integer id){
        Passenger passengerUpdate = new Passenger(updatedUserDTO);
        User updatedUser = passengerService.update(id, passengerUpdate);
        if (updatedUser != null){
            return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(updatedUser), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AllRidesOut> getPassengerRides(@PathVariable Integer id, @RequestParam int page,
                                                  @RequestParam int size, @RequestParam String sort,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Page<Ride> rides = rideService.getPassengerRides(id, from, to,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort)));

        return new ResponseEntity<>(new AllRidesOut(rides), HttpStatus.OK);
    }
}
