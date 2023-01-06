package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.LocationInOut;
import org.yugo.backend.YuGo.dto.VehicleIn;
import org.yugo.backend.YuGo.mapper.LocationMapper;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.VehicleService;

import java.util.HashMap;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final DriverService driverService;
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService, DriverService driverService){
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }
    @PutMapping(
            value = "/{id}/location",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<Void> changeLocation(@RequestBody LocationInOut locationInOut, @PathVariable Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        vehicle.setCurrentLocation(LocationMapper.fromDTOtoLocation(locationInOut));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(
            value = "/{id}/changeVehicle",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity makeVehicleChangeRequest(@PathVariable Integer id, @RequestBody VehicleIn vehicleIn){
        Vehicle vehicle = new Vehicle(vehicleIn);
        vehicleService.insertVehicle(vehicle);
        Driver driver = driverService.getDriver(id).get(); //TODO promeniti kad servis uradi handling
        VehicleChangeRequest vehicleChangeRequest = new VehicleChangeRequest(driver, vehicle);
        vehicleService.insertVehicleChangeRequest(vehicleChangeRequest);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Vehicle request created successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
