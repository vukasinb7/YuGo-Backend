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
import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.mapper.WorkingTimeMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.DriverService;
import org.yugo.backend.YuGo.service.RideService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;
    private final DocumentService documentService;
    private final RideService rideService;
    @Autowired
    public DriverController(DriverService driverService, DocumentService documentService, RideService rideService) {
        this.driverService = driverService;
        this.documentService = documentService;
        this.rideService = rideService;
    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetailedInOut> createDriver(@RequestBody @Valid UserDetailedIn driverIn){
        Driver driver = new Driver(driverIn);
        Driver driverNew = driverService.insertDriver(driver);
        return new ResponseEntity<>(new UserDetailedInOut(driverNew), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER', 'DRIVER')")
    public ResponseEntity<UserDetailedInOut> getDriver(@PathVariable @NotNull @Positive Integer id){
        Driver driver = driverService.getDriver(id);
        return new ResponseEntity<>(new UserDetailedInOut(driver), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<VehicleOut> getVehicle(@PathVariable Integer id){
        Vehicle vehicle = driverService.getDriverVehicle(id);
        return new ResponseEntity<>(new VehicleOut(vehicle), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/vehicle",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<VehicleOut> createVehicle(@PathVariable @NotNull @Positive Integer id,
                                                    @RequestBody @Valid VehicleIn vehicleIn){
        Driver driver = driverService.getDriver(id);
        Vehicle vehicle = new Vehicle(vehicleIn);
        Vehicle vehicleUpdated = driverService.changeVehicle(driver, vehicle);
        return new ResponseEntity<>(new VehicleOut(vehicleUpdated), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')") // NE KORISTITI OVO, KORISTI uploadDocument
    public ResponseEntity<DocumentOut> createDocument(@PathVariable @NotNull @Positive Integer id, @RequestBody @Valid DocumentIn documentIn) throws IOException {
        Driver driver = driverService.getDriver(id);
        Document document = new Document(documentIn.getName(), documentIn.getDocumentImage(), driver,DocumentType.DRIVING_LICENCE);
        documentService.insert(document);
        return new ResponseEntity<>(new DocumentOut(document), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/document/{documentType}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<DocumentOut> uploadDocument(@PathVariable Integer id,@PathVariable String documentType,@RequestParam("image") MultipartFile file)
            throws IOException {
        String path="src\\main\\resources\\img\\"+id+"_"+documentType+".jpg";
        Document document= new Document(id+"_"+documentType+".jpg",path,driverService.getDriver(id),DocumentType.valueOf(documentType));
        documentService.upload(document,file);
        return new ResponseEntity<>(new DocumentOut(document), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/documents",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<List<DocumentOut>> getDocuments(@PathVariable Integer id){
        Driver driver = driverService.getDriver(id);
        List<DocumentOut> documents = new ArrayList<>();
        for(Document d : driver.getDocuments()){
            documents.add(new DocumentOut(d));
        }

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/document/{document-id}"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<Void> deleteDocument(@PathVariable(name = "document-id") Integer documentId){
        documentService.delete(documentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AllUsersOut> getDrivers(@RequestParam int page, @RequestParam int size){
        Page<User> drivers = driverService.getDriversPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(drivers), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    public ResponseEntity<UserDetailedInOut> updateDriver(@PathVariable @NotNull @Positive Integer id,
                                                   @RequestBody @Valid UserDetailedIn driverDTO){
        Driver driverUpdate = UserDetailedMapper.fromDTOtoDriver(driverDTO);
        driverUpdate.setId(id);
        User userUpdated = driverService.updateDriver(driverUpdate);
        if(userUpdated == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(userUpdated), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/vehicle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<VehicleOut> updateVehicle(@PathVariable @NotNull @Positive Integer id,
                                             @RequestBody @Valid VehicleIn vehicleIn){
        Driver driver = driverService.getDriver(id);
        Vehicle vehicle = new Vehicle(vehicleIn);
        Vehicle vehicleUpdated = driverService.changeVehicle(driver, vehicle);

        return new ResponseEntity<>(new VehicleOut(vehicleUpdated), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/working-hour",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<AllWorkTimeOut> getWorkingTimes(@PathVariable(value = "id") Integer id,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size,
                                                      @RequestParam(name = "from") String from,
                                                      @RequestParam(name = "to") String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime = LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);
        LocalDateTime toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        Page<WorkTime> workTimes = driverService.getDriverWorkingTimesPage(id, PageRequest.of(page, size), fromTime, toTime);
        return new ResponseEntity<>(new AllWorkTimeOut(workTimes.stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/working-hour",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<WorkTimeOut> createWorkTimeForDriver(@PathVariable @NotNull @Positive Integer id,
                                                        @RequestBody @Valid WorkTimeIn workTimeIn){
        WorkTime wt = new WorkTime();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        wt.setStartTime(LocalDateTime.parse(workTimeIn.getStart(), formatter));

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
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<WorkTimeOut> getWorkTimeById(@PathVariable(value = "working-hour-id") Integer id){
        WorkTime workTime = driverService.getWorkTime(id);
        return new ResponseEntity<>(WorkingTimeMapper.fromWorkTimeToDTO(workTime), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    ResponseEntity<AllRidesOut> getRidesForDriver(@PathVariable(value = "id") Integer id,
                                                  @RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size,
                                                  @RequestParam(name = "sort", required = false) String sort,
                                                  @RequestParam(name = "from") String from,
                                                  @RequestParam(name = "to") String to){
        PageRequest pageRequest;
        pageRequest = PageRequest.of(page, size, Sort.by(Objects.requireNonNullElse(sort, "id")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime = LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);
        LocalDateTime toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        Page<Ride> rides = rideService.getRidesByDriverPage(id, pageRequest, fromTime, toTime);
        return new ResponseEntity<>(new AllRidesOut(rides), HttpStatus.OK);
    }

    @PutMapping(
            value = "/working-hour/{working-hour-id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<WorkTimeOut> updateWorkTime(@PathVariable(value = "working-hour-id") @NotNull @Positive Integer id, @RequestBody @Valid EndWorkTimeIn workTimeIn){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime endTime = LocalDateTime.parse(workTimeIn.getEnd(), formatter);
        WorkTime workTimeUpdated = driverService.endWorkTime(id, endTime);
        return new ResponseEntity<>(WorkingTimeMapper.fromWorkTimeToDTO(workTimeUpdated), HttpStatus.OK);
    }
}
