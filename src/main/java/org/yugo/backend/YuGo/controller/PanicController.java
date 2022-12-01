package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.AllPanicsResponse;
import org.yugo.backend.YuGo.dto.AllUsersResponse;
import org.yugo.backend.YuGo.service.*;

@RestController
@RequestMapping("/api/panic")
public class PanicController {
    private final PanicService panicService;

    @Autowired
    public PanicController(PanicServiceImpl panicServiceImpl){
        this.panicService = panicServiceImpl;
    }

    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllPanicsResponse> getPanics(){
        return new ResponseEntity<>(new AllPanicsResponse(panicService.getAll()), HttpStatus.OK);
    }
}
