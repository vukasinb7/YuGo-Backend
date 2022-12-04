package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.LocationRespone;
import org.yugo.backend.YuGo.dto.ReasonRequest;
import org.yugo.backend.YuGo.dto.RideResponse;
import org.yugo.backend.YuGo.mapper.LocationResponseMapper;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.RideStatus;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService=vehicleService;
    }
    @PutMapping(
            value = "/{id}/location",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeLocation(@RequestBody LocationRespone locationRespone, @PathVariable Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        vehicle.setCurrentLocation(LocationResponseMapper.fromDTOtoLocation(locationRespone));
        return new ResponseEntity<>(null, HttpStatus.OK);

    }


}
