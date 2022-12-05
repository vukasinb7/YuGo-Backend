package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.VehicleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final DocumentService documentService;

    @Autowired
    public DriverController(DriverService driverService, VehicleService vehicleService, DocumentService documentService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.documentService = documentService;
    }

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> createDriver(@RequestBody UserDetailedIn driver){
        Driver newDriver = new Driver(driver);
        driverService.insertDriver(newDriver);
        return new ResponseEntity<UserDetailedInOut>(new UserDetailedInOut(newDriver), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> getDriver(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<UserDetailedInOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            response = new ResponseEntity<>(new UserDetailedInOut(driver), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VehicleOut> getVehicle(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        Vehicle vehicle = driver.getVehicle();
        response = new ResponseEntity<>(new VehicleOut(vehicle), HttpStatus.OK);
        return response;
    }
    @PostMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VehicleOut> createVehicle(@PathVariable Integer id, @RequestBody VehicleIn vehicleIn){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Vehicle vehicle = new Vehicle(vehicleIn);
        vehicle.setDriver(driver);
        vehicleService.insertVehicle(vehicle);

        driver.setVehicle(vehicle);
        driverService.updateDriver(driver);

        response = new ResponseEntity<>(new VehicleOut(vehicle), HttpStatus.OK);
        return response;
    }
    /*      TODO Pitanja
    *   1. Prilikom kreiranje novog vozila nema smisla prosledjivati i trenutnu lokaciju vozila
    *   2. Prilikom kreiranja novog dokumenta id vozaca se prosledjuje i kao PathVariable i u okviru RequestBody
    *   3. Prilikom izmene podatak o vozacu da li je potrebno slati id vozaca u okviru RequestBody
    *   4. /api/driver/{diver-id}/working-hour/{working-hour-id} ako dobavljamo podatke preko working-hour-id cemu sluzi driver-id?????
    */

    @PostMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DocumentOut> createDocument(@PathVariable Integer id, @RequestBody DocumentIn documentIn){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<DocumentOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Document document = new Document(documentIn.getName(), documentIn.getDocumentImage(), driver);
        documentService.insert(document);

        response = new ResponseEntity<>(new DocumentOut(document), HttpStatus.OK);
        return response;
    }

    @GetMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Iterable<DocumentOut>> getDocuments(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<Iterable<DocumentOut>> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        List<DocumentOut> documents = new ArrayList<>();
        for(Document d : driver.getDocuments()){
            documents.add(new DocumentOut(d));
        }

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/{id}/documents"
    )
    ResponseEntity<Void> deleteDocuments(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        if(driver == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        documentService.deleteAllForDriver(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<UserDetailedInOut>> getDrivers(@RequestParam int page, @RequestParam int size){
        Page<User> drivers = driverService.getDriversPage(PageRequest.of(page, size));
        List<UserDetailedInOut> output = new ArrayList<>();
        for(User u : drivers){
            output.add(new UserDetailedInOut(u));
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserDetailedInOut> updateDriver(@PathVariable Integer id, @RequestBody UserDetailedInOut driverDTO){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<UserDetailedInOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        driver.setName(driverDTO.getName());
        driver.setSurName(driverDTO.getSurname());
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setTelephoneNumber(driverDTO.getTelephoneNumber());
        driver.setEmail(driverDTO.getEmail());
        driver.setAddress(driverDTO.getAddress());
        driverService.updateDriver(driver);
        return new ResponseEntity<>(new UserDetailedInOut(driver), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/vehicle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<VehicleOut> updateVehicle(@PathVariable Integer id, @RequestBody VehicleIn vehicleIn){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Vehicle vehicle = driver.getVehicle();
        Integer vehicleId = null;
        if(vehicle != null){
            vehicleId = vehicle.getId();
        }

        vehicle = new Vehicle(vehicleIn);
        vehicle.setDriver(driver);
        vehicle.setId(vehicleId);
        vehicleService.insertVehicle(vehicle);

        driver.setVehicle(vehicle);
        driverService.updateDriver(driver);

        response = new ResponseEntity<>(new VehicleOut(vehicle), HttpStatus.OK);
        return response;
    }

    @GetMapping(
            value = "/{id}/working-hours",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<WorkingTimeOut>> getWorkingHours(@PathVariable(value = "id") Integer id, @RequestParam int page, @RequestParam int size, @RequestParam String from, @RequestParam String to){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<List<WorkingTimeOut>> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        LocalDateTime fromLDT = LocalDateTime.parse(from);
        LocalDateTime toLDT = LocalDateTime.parse(to);
        Page<WorkTime> workTimes = driverService.getDriverWorkingTimesPage(id, PageRequest.of(page, size), fromLDT, toLDT);
        List<WorkingTimeOut> output = new ArrayList<>();
        for(WorkTime wt : workTimes){
            output.add(new WorkingTimeOut(wt));
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/working-hours",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkingTimeOut> createDriver(@PathVariable Integer id, @RequestBody WorkingTimeIn workingTimeIn){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<WorkingTimeOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        WorkTime wt = new WorkTime();
        wt.setDriver(driver);
        wt.setStartTime(LocalDateTime.parse(workingTimeIn.getStart()));
        wt.setEndTime(LocalDateTime.parse(workingTimeIn.getEnd()));

        WorkingTimeOut output = new WorkingTimeOut(driverService.insertWorkTime(wt));
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
