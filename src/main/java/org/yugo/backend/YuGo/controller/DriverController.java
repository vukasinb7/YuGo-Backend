package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.UserRequest;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.service.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> createDriver(@RequestBody UserRequest driver){
        Driver newDriver = new Driver();
        newDriver.setName(driver.getName());
        newDriver.setLastName(driver.getLastName());
        newDriver.setProfilePicture(driver.getProfilePicture());
        newDriver.setPhone(driver.getPhone());
        newDriver.setEmail(driver.getEmail());
        newDriver.setAddress(driver.getAddress());
        newDriver.setPassword(driver.getPassword());
        driverService.addDriver(newDriver);

        return new ResponseEntity<UserResponse>(new UserResponse(newDriver), HttpStatus.OK);
    }
}
