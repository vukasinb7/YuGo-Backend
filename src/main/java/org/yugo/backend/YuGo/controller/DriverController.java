package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.WorkingTimeMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.RideService;
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
    private final RideService rideService;
    @Autowired
    public DriverController(DriverService driverService, VehicleService vehicleService, DocumentService documentService, RideService rideService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.documentService = documentService;
        this.rideService = rideService;
    }

    @PostMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> createDriver(@RequestBody UserDetailedIn driver){
        Driver newDriver = new Driver(driver);
        driverService.insertDriver(newDriver);
        return new ResponseEntity<>(new UserDetailedInOut(newDriver), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDetailedInOut> getDriver(@PathVariable Integer id){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        if(driver == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDetailedInOut(driver), HttpStatus.OK);

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
    ResponseEntity<AllWorkTimeOut> getWorkingTimes(@PathVariable(value = "id") Integer id,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size,
                                                      @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                      @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<AllWorkTimeOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

        Page<WorkTime> workTimes = driverService.getDriverWorkingTimesPage(id, PageRequest.of(page, size), from, to);
        return new ResponseEntity<>(new AllWorkTimeOut(workTimes.stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/working-hours",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> getWorkTimeForDriver(@PathVariable Integer id, @RequestBody WorkTimeIn workTimeIn){
        Driver driver = (Driver) driverService.getDriver(id).orElse(null);
        ResponseEntity<WorkTimeOut> response;
        if(driver == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        WorkTime wt = new WorkTime();
        wt.setDriver(driver);
        wt.setStartTime(LocalDateTime.parse(workTimeIn.getStart()));
        wt.setEndTime(LocalDateTime.parse(workTimeIn.getEnd()));

        WorkTimeOut output = new WorkTimeOut(driverService.insertWorkTime(wt));
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping(
            value = "/working-hour/{working-hour-id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> getWorkTimeById(@PathVariable(value = "working-hour-id") Integer id){
        WorkTime wt = driverService.getWorkTime(id).orElse(null);
        ResponseEntity<WorkTimeOut> response;
        if(wt == null){
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }
        response = new ResponseEntity<>(WorkingTimeMapper.fromWorkTimeToDTO(wt), HttpStatus.OK);
        return response;
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AllRidesOut> getRidesForDriver(@PathVariable(value = "id") Integer id,
                                                  @RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size,
                                                  @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                  @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Page<Ride> rides = rideService.getRidesByDriverPage(id, PageRequest.of(page, size), from, to);
        return new ResponseEntity<>(new AllRidesOut(rides.stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/working-hour/{working-hour-id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> updateWorkTime(@PathVariable(value = "working-hour-id") Integer id, @RequestBody WorkTimeIn workTimeIn){
        WorkTime workTime = WorkingTimeMapper.fromDTOtoWorkTime(workTimeIn);
        WorkTime workTimeUpdated = driverService.updateWorkTime(workTime);
        if(workTimeUpdated == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(WorkingTimeMapper.fromWorkTimeToDTO(workTimeUpdated), HttpStatus.OK);
    }
}
