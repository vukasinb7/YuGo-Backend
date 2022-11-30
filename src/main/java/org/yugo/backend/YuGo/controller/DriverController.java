package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.model.Document;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.VehicleService;

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
    public ResponseEntity<UserResponse> createDriver(@RequestBody UserRequest driver){
        Driver newDriver = new Driver(driver);
        driverService.addDriver(newDriver);
        return new ResponseEntity<UserResponse>(new UserResponse(newDriver), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> getDriver(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<UserResponse> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            response = new ResponseEntity<>(new UserResponse(driver), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VehicleRespone> getVehicle(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleRespone> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        Vehicle vehicle = driver.getVehicle();
        response = new ResponseEntity<>(new VehicleRespone(vehicle), HttpStatus.OK);
        return response;
    }
    @PostMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VehicleRespone> createVehicle(@PathVariable Integer id, @RequestBody VehicleRequest vehicleRequest){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleRespone> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Vehicle vehicle = new Vehicle(vehicleRequest);
        vehicle.setDriver(driver);
        vehicleService.saveVehicle(vehicle);

        driver.setVehicle(vehicle);
        driverService.updateDriver(driver);

        response = new ResponseEntity<>(new VehicleRespone(vehicle), HttpStatus.OK);
        return response;
    }
    /*      TODO Pitanja
    *   1. Prilikom kreiranje novog vozila nema smisla prosledjivati i trenutnu lokaciju vozila
    *   2. Prilikom kreiranja novog dokumenta id vozaca se prosledjuje i kao PathVariable i u okviru RequestBody
    *   3. Cascade ALL ne funkcionise
    *   4. LazyLoading ne funkcionise uopste
    *   5. WorkingHours i Driver treba da bude ManyToOne a ne OneToOne veza
    *   6. Prilikom izmene podatak o vozacu da li je potrebno slati id vozaca u okviru RequestBody
    */

    @PostMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DocumentResponse> createDocument(@PathVariable Integer id, @RequestBody DocumentRequest documentRequest){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<DocumentResponse> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Document document = new Document(documentRequest.getName(), documentRequest.getDocumentImage(), driver);
        documentService.add(document);

        response = new ResponseEntity<>(new DocumentResponse(document), HttpStatus.OK);
        return response;
    }

    @GetMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Iterable<DocumentResponse>> getDocuments(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<Iterable<DocumentResponse>> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        List<DocumentResponse> documents = new ArrayList<>();
        for(Document d : driver.getDocuments()){
            documents.add(new DocumentResponse(d));
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
    ResponseEntity<List<UserResponse>> getDrivers(@RequestParam int page, @RequestParam int size){
        Page<User> drivers = driverService.getDriversPage(PageRequest.of(page, size));
        List<UserResponse> output = new ArrayList<>();
        for(User u : drivers){
            output.add(new UserResponse(u));
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> updateDriver(@PathVariable Integer id, @RequestBody UserResponse driverDTO){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<UserResponse> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        driver.setName(driverDTO.getName());
        driver.setLastName(driverDTO.getLastName());
        driver.setProfilePicture(driverDTO.getProfilePicture());
        driver.setPhone(driverDTO.getPhone());
        driver.setEmail(driverDTO.getEmail());
        driver.setAddress(driverDTO.getAddress());
        driverService.updateDriver(driver);
        return new ResponseEntity<>(new UserResponse(driver), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/vehicle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<VehicleRespone> updateVehicle(@PathVariable Integer id, @RequestBody VehicleRequest vehicleRequest){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<VehicleRespone> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Vehicle vehicle = driver.getVehicle();
        Integer vehicleId = null;
        if(vehicle != null){
            vehicleId = vehicle.getId();
        }

        vehicle = new Vehicle(vehicleRequest);
        vehicle.setDriver(driver);
        vehicle.setId(vehicleId);
        vehicleService.saveVehicle(vehicle);

        driver.setVehicle(vehicle);
        driverService.updateDriver(driver);

        response = new ResponseEntity<>(new VehicleRespone(vehicle), HttpStatus.OK);
        return response;
    }
}
