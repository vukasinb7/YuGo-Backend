package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.MultipleUsersResponse;
import org.yugo.backend.YuGo.dto.UserRequest;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;
import org.yugo.backend.YuGo.service.PassengerService;
import org.yugo.backend.YuGo.service.PassengerServiceImpl;
import org.yugo.backend.YuGo.service.UserService;
import org.yugo.backend.YuGo.service.UserServiceImpl;

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
        Passenger newPass = new Passenger();
        newPass.setName(user.getName());
        newPass.setLastName(user.getLastName());
        newPass.setProfilePicture(user.getProfilePicture());
        newPass.setPhone(user.getPhone());
        newPass.setEmail(user.getEmail());
        newPass.setAddress(user.getAddress());
        newPass.setPassword(user.getPassword());
        passengerService.add(newPass);
        return new ResponseEntity<>(new UserResponse(newPass), HttpStatus.OK);
    }

    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MultipleUsersResponse> getPassengers(){
        return new ResponseEntity<>(new MultipleUsersResponse(passengerService.getAll()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{activationId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity ActivatePassenger(@PathVariable Integer activationId){
        UserActivation userActivation = userService.getUserActivation(activationId).get();
        userActivation.getUser().setActive(true);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> getPassengers(@PathVariable Integer id){
        return new ResponseEntity<>(new UserResponse(passengerService.get(id).get()), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> UpdatePassenger(@RequestBody UserRequest updatedUser, @PathVariable Integer id){
        User user = passengerService.get(id).get();
        user.setName(updatedUser.getName());
        user.setLastName(updatedUser.getLastName());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setPhone(updatedUser.getPhone());
        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        user.setPassword(updatedUser.getPassword());
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }
}
