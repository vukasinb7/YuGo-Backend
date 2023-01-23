package org.yugo.backend.YuGo.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AllPanicsOut;
import org.yugo.backend.YuGo.dto.PanicOut;
import org.yugo.backend.YuGo.mapper.PanicMapper;
import org.yugo.backend.YuGo.service.*;

@RestController
@RequestMapping("/api/panic")
public class PanicController {
    private final PanicService panicService;

    @Autowired
    public PanicController(PanicService panicService){
        this.panicService = panicService;
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllPanicsOut> getPanics(@Min(value=0, message = "Page must be 0 or greater")
                                              @NotNull(message = "Field (page) is required")
                                              @RequestParam(name="page") int page,
                                              @Positive(message = "Size must be positive")
                                              @NotNull(message = "Field (size) is required")
                                              @RequestParam(name="size") int size){
        return new ResponseEntity<>(new AllPanicsOut(panicService.getAll(PageRequest.of(page, size))), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PanicOut> getPanic(@PathVariable Integer id){
        return new ResponseEntity<>(PanicMapper.fromPanicToDTO(panicService.get(id)), HttpStatus.OK);
    }

    @GetMapping(
            value = "/ride/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PanicOut> getPanicByRideId(@PathVariable Integer id){
        return new ResponseEntity<>(PanicMapper.fromPanicToDTO(panicService.getByRideId(id)), HttpStatus.OK);
    }
}
