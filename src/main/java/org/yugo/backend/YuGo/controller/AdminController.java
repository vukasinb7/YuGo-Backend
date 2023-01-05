package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.UserDetailedIn;
import org.yugo.backend.YuGo.dto.UserDetailedInOut;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Admin;
import org.yugo.backend.YuGo.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetailedInOut> getAdmin(@PathVariable Integer id){
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(adminService.get(id)), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetailedInOut> updateAdmin(@RequestBody UserDetailedIn updatedAdminDTO, @PathVariable Integer id){
        Admin adminUpdate = new Admin(updatedAdminDTO);
        adminUpdate.setId(id);
        Admin updatedAdmin = adminService.update(adminUpdate);
        return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(updatedAdmin), HttpStatus.OK);
    }
}
