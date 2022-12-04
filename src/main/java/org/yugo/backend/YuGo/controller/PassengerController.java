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
    public PassengerController(PassengerServiceImpl passengerServiceImpl, UserServiceImpl userServiceImpl, RideServiceImpl rideServiceImpl){
        this.passengerService = passengerServiceImpl;
        this.userService = userServiceImpl;
        this.rideService = rideServiceImpl;
    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> addPassenger(@RequestBody UserDetailedIn user){
        Passenger newPass = new Passenger(user);
        passengerService.insert(newPass);
        UserActivation newAct = new UserActivation(newPass, LocalDateTime.now(), Duration.ZERO);
        userService.saveUserActivation(newAct);
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(newPass), HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersOut> getAllPassengers(@RequestParam int page, @RequestParam int size){
        Page<User> passengers = passengerService.getPassengersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(passengers.get()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{activationId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity activatePassenger(@PathVariable Integer activationId){
        Optional<UserActivation> userActivation = userService.getUserActivation(activationId);
        if (userActivation.isPresent()){
            userActivation.get().getUser().setActive(true);
            userService.saveUser(userActivation.get().getUser());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> getPassenger(@PathVariable Integer id){
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(passengerService.get(id).get()), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> updatePassenger(@RequestBody UserDetailedIn updatedUser, @PathVariable Integer id){
        User user = passengerService.get(id).get();
        user.setName(updatedUser.getName());
        user.setSurName(updatedUser.getSurName());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setTelephoneNumber(updatedUser.getTelephoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        user.setPassword(updatedUser.getPassword());
        passengerService.insert(user);
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(user), HttpStatus.OK);
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

        return new ResponseEntity<>(new AllRidesOut(rides.stream()), HttpStatus.OK);
    }
}
