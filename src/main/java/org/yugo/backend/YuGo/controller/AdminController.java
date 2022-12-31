package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.UserDetailedIn;
import org.yugo.backend.YuGo.dto.UserDetailedInOut;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Admin;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, RoleService roleService,
                               BCryptPasswordEncoder passwordEncoder){
        this.adminService = adminService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetailedInOut> getAdmin(@PathVariable Integer id){
        Optional<Admin> userOpt = adminService.get(id);
        if (userOpt.isPresent()){
            return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(userOpt.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
        if (updatedAdmin != null){
            return new ResponseEntity<>(UserDetailedMapper.fromUsertoDTO(updatedAdmin), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
