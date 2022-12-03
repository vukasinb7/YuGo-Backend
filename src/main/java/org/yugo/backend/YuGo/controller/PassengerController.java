package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AllUsersResponse;
import org.yugo.backend.YuGo.dto.UserRequest;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.service.PassengerService;
import org.yugo.backend.YuGo.service.PassengerServiceImpl;
import org.yugo.backend.YuGo.service.UserService;
import org.yugo.backend.YuGo.service.UserServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerService passengerService;
    private final UserService userService;

    @Autowired
    public PassengerController(PassengerServiceImpl passengerServiceImpl, UserServiceImpl userServiceImpl){
        this.passengerService = passengerServiceImpl;
        this.userService = userServiceImpl;
    }

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> addPassenger(@RequestBody UserRequest user){
        Passenger newPass = new Passenger(user);
        passengerService.insert(newPass);
        UserActivation newAct = new UserActivation(newPass, LocalDateTime.now(), Duration.ZERO);
        userService.saveUserActivation(newAct);
        return new ResponseEntity<>(new UserResponse(newPass), HttpStatus.OK);
    }

    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersResponse> getAllPassengers(@RequestParam int page, @RequestParam int size){
        Page<User> passengers = passengerService.getPassengersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersResponse(passengers.get()), HttpStatus.OK);
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
    public ResponseEntity<UserResponse> getPassenger(@PathVariable Integer id){
        return new ResponseEntity<>(new UserResponse(passengerService.get(id).get()), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> updatePassenger(@RequestBody UserRequest updatedUser, @PathVariable Integer id){
        User user = passengerService.get(id).get();
        user.setName(updatedUser.getName());
        user.setSurName(updatedUser.getSurName());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setTelephoneNumber(updatedUser.getTelephoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        user.setPassword(updatedUser.getPassword());
        passengerService.insert(user);
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

//    @GetMapping(
//            value = "/{id}/ride",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    ResponseEntity<List<UserResponse>> getPassengerRides(@PathVariable Integer id, @RequestParam int page,
//                                                         @RequestParam int size, @RequestParam String sort,
//                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
//                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
//        Page<Ride> rides = passengerService.getPassengerRidesPage(id, from, to,
//                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort)));
//
//
//        List<UserResponse> output = new ArrayList<>();
//        for(User u : drivers){
//            output.add(new UserResponse(u));
//        }
//        return new ResponseEntity<>(output, HttpStatus.OK);
//    }
}
