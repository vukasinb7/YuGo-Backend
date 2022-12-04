package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.AllUserMessagesResponse;
import org.yugo.backend.YuGo.dto.AllUsersResponse;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.MessageService;
import org.yugo.backend.YuGo.service.MessageServiceImpl;
import org.yugo.backend.YuGo.service.UserService;
import org.yugo.backend.YuGo.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, MessageServiceImpl messageServiceImpl){
        this.userService = userServiceImpl;
        this.messageService = messageServiceImpl;
    }
    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersResponse> getAllUsers(@RequestParam int page, @RequestParam int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersResponse(users.get()), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUserMessagesResponse> getUserMessages(@PathVariable Integer id){
        return new ResponseEntity<>(new AllUserMessagesResponse(messageService.getUserMessages(id).stream()), HttpStatus.OK);
    }
}
