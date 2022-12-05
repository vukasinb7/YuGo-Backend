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
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.mapper.WorkingTimeMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.RideService;
import org.yugo.backend.YuGo.service.VehicleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<UserDetailedInOut> createDriver(@RequestBody UserDetailedIn driverIn){
        Driver driver = UserDetailedMapper.fromDTOtoDriver(driverIn);
        Driver driverNew = driverService.insertDriver(driver);
        return new ResponseEntity<>(new UserDetailedInOut(driverNew), HttpStatus.OK);
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
        Optional<User> driverOpt = driverService.getDriver(id);

        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Driver driver = (Driver) driverOpt.get();
        Vehicle vehicle = driver.getVehicle();
        return new ResponseEntity<>(new VehicleOut(vehicle), HttpStatus.OK);
    }
    @PostMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VehicleOut> createVehicle(@PathVariable Integer id, @RequestBody VehicleIn vehicleIn){
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Driver driver = (Driver)driverOpt.get();
        Vehicle vehicle = new Vehicle(vehicleIn);
        Vehicle vehicleUpdated = driverService.changeVehicle(driver, vehicle);
        return new ResponseEntity<>(new VehicleOut(vehicleUpdated), HttpStatus.OK);
    }
    @PostMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DocumentOut> createDocument(@PathVariable Integer id, @RequestBody DocumentIn documentIn){
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Driver driver = (Driver)driverOpt.get();
        Document document = new Document(documentIn.getName(), documentIn.getDocumentImage(), driver);
        documentService.insert(document);

        return new ResponseEntity<>(new DocumentOut(document), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Iterable<DocumentOut>> getDocuments(@PathVariable Integer id){
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Driver driver = (Driver) driverOpt.get();
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
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        documentService.deleteAllForDriver(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AllUsersOut> getDrivers(@RequestParam int page, @RequestParam int size){
        Page<User> drivers = driverService.getDriversPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(drivers), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserDetailedInOut> updateDriver(@PathVariable Integer id, @RequestBody UserDetailedIn driverDTO){
        Driver driver = (Driver) UserDetailedMapper.fromDTOtoDriver(driverDTO);
        driver.setId(id);
        User userUpdated = driverService.updateDriver(driver);
        if(userUpdated == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(driver), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/vehicle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<VehicleOut> updateVehicle(@PathVariable Integer id, @RequestBody VehicleIn vehicleIn){
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Driver driver = (Driver) driverOpt.get();
        Vehicle vehicle = new Vehicle(vehicleIn);
        Vehicle vehicleUpdated = driverService.changeVehicle(driver, vehicle);

        return new ResponseEntity<>(new VehicleOut(vehicleUpdated), HttpStatus.OK);
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
        Optional<User> driverOpt = driverService.getDriver(id);
        if(driverOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Page<WorkTime> workTimes = driverService.getDriverWorkingTimesPage(id, PageRequest.of(page, size), from, to);
        return new ResponseEntity<>(new AllWorkTimeOut(workTimes.stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/working-hours",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> createWorkTimeForDriver(@PathVariable Integer id, @RequestBody WorkTimeIn workTimeIn){
        WorkTime wt = new WorkTime();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        wt.setStartTime(LocalDateTime.parse(workTimeIn.getStart(), formatter));
        wt.setEndTime(LocalDateTime.parse(workTimeIn.getEnd(), formatter));
        WorkTime workTimeNew = driverService.insertWorkTime(id, wt);
        if(workTimeNew == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        WorkTimeOut output = new WorkTimeOut(workTimeNew);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping(
            value = "/working-hour/{working-hour-id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> getWorkTimeById(@PathVariable(value = "working-hour-id") Integer id){
        Optional<WorkTime> workTimeOpt = driverService.getWorkTime(id);
        if(workTimeOpt.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(WorkingTimeMapper.fromWorkTimeToDTO(workTimeOpt.get()), HttpStatus.OK);
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
        return new ResponseEntity<>(new AllRidesOut(rides), HttpStatus.OK);
    }

    @PutMapping(
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
