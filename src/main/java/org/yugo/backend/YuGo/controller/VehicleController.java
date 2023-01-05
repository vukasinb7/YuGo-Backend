package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.LocationInOut;
import org.yugo.backend.YuGo.mapper.LocationMapper;
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
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<Void> changeLocation(@RequestBody LocationInOut locationInOut, @PathVariable Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        vehicle.setCurrentLocation(LocationMapper.fromDTOtoLocation(locationInOut));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
