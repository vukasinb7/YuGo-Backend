package org.yugo.backend.YuGo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.annotation.AuthorizeSelf;
import org.yugo.backend.YuGo.annotation.AuthorizeSelfAndAdmin;
import org.yugo.backend.YuGo.dto.AllVehicleChangeRequestsOut;
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
    public ResponseEntity<Void> changeLocation(@RequestBody @Valid LocationInOut locationInOut,
                                               @NotNull(message = "Field (id) is required")
                                               @Positive(message = "Id must be positive")
                                               @PathVariable(value="id") Integer id){
        Vehicle vehicle=vehicleService.getVehicle(id);
        vehicle.setCurrentLocation(LocationMapper.fromDTOtoLocation(locationInOut));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            value = "/{id}/makeRequest",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @AuthorizeSelfAndAdmin(pathToUserId = "[0]", message = "User not found!")
    public ResponseEntity makeVehicleChangeRequest(@PathVariable @NotNull @Positive Integer id,
                                                   @RequestBody @Valid VehicleIn vehicleIn){
        Vehicle vehicle = new Vehicle(vehicleIn);
        vehicleService.insertVehicle(vehicle);
        Driver driver = driverService.getDriver(id);
        VehicleChangeRequest vehicleChangeRequest = new VehicleChangeRequest(driver, vehicle);
        vehicleService.insertVehicleChangeRequest(vehicleChangeRequest);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Vehicle request created successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            value = "/changeRequests",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllVehicleChangeRequestsOut> getAllVehicleChangeRequests(@RequestParam int page, @RequestParam int size){
        Page<VehicleChangeRequest> vehicleChangeRequestPage = vehicleService.
                getAllVehicleChangeRequests(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"driver" ,"dateCreated")));
        return new ResponseEntity<>(new AllVehicleChangeRequestsOut(vehicleChangeRequestPage), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{requestId}/acceptRequest",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity acceptVehicleChangeRequest(@PathVariable Integer requestId){
        vehicleService.acceptVehicleChangeRequest(requestId);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Driver vehicle changed successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/{requestId}/rejectRequest",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity rejectVehicleChangeRequest(@PathVariable Integer requestId){
        vehicleService.rejectVehicleChangeRequest(requestId);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Vehicle change request rejected successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
