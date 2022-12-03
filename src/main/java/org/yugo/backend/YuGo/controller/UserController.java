package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.AllUsersResponse;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.UserService;
import org.yugo.backend.YuGo.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userService = userServiceImpl;
    }
    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersResponse> getAllUsers(@RequestParam int page, @RequestParam int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersResponse(users.get()), HttpStatus.OK);
    }
}
