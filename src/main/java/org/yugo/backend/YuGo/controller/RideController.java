package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.AllUsersResponse;
import org.yugo.backend.YuGo.dto.RideResponse;
import org.yugo.backend.YuGo.service.RideService;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService){
        this.rideService=rideService;
    }

    @GetMapping(
            value = "/active/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RideResponse> getRidesByDriver(@PathVariable Integer id){
        return new ResponseEntity<>(new RideResponse(rideService.getActiveRideByDriver(id)), HttpStatus.OK);
    }

}
