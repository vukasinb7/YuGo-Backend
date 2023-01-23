package org.yugo.backend.YuGo.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AllRideReviewsOut;
import org.yugo.backend.YuGo.dto.ReportOut;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.service.ReportService;
import org.yugo.backend.YuGo.service.ReviewService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @GetMapping(
            value = "/{id}/ridesPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<ReportOut> getRidesPerDay(@NotNull(message = "Field (id) is required")
                                                                           @Positive(message = "Id must be positive")
                                                                           @PathVariable(value="id") Integer id,
                                                                     @RequestParam(name = "from", required = false) String from,
                                                                     @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getNumberOfRidesByUser(id,fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(
            value = "/totalRidesPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportOut> getTotalRidesPerDay(@RequestParam(name = "from", required = false) String from,
                                                         @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getTotalNumberOfRides(fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }


    @GetMapping(
            value = "/{id}/kilometersPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<ReportOut> getKilometersPerDay(@NotNull(message = "Field (id) is required")
                                                    @Positive(message = "Id must be positive")
                                                    @PathVariable(value="id") Integer id,
                                                    @RequestParam(name = "from", required = false) String from,
                                                    @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getDistanceByUser(id,fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(
            value = "/totalKilometersPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportOut> getKilometersPerDay(@RequestParam(name = "from", required = false) String from,
                                                         @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getTotalDistance(fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/totalCostPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<ReportOut> getTotalCostByUser(@NotNull(message = "Field (id) is required")
                                                         @Positive(message = "Id must be positive")
                                                         @PathVariable(value="id") Integer id,
                                                         @RequestParam(name = "from", required = false) String from,
                                                         @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getTotalCostOfRidesByUser(id,fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(
            value = "totalCostPerDay",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportOut> getTotalCostByUser(
                                                        @RequestParam(name = "from", required = false) String from,
                                                        @RequestParam(name = "to", required = false) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime;
        LocalDateTime toTime;
        if (from==null)
            fromTime=LocalDateTime.of(1753, Month.JANUARY,1,0, 0);
        else
            fromTime= LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);

        if (to==null)
            toTime=LocalDateTime.of(9998, Month.DECEMBER,31,0,0);
        else
            toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        ReportOut results= reportService.getTotalCostOfRides(fromTime,toTime);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
