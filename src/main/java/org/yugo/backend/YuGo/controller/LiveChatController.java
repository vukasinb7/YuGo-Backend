package org.yugo.backend.YuGo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.LiveMessageIn;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.WebSocketService;

import java.util.HashMap;

@RestController
@RequestMapping("/api/liveChat")
public class LiveChatController {
    WebSocketService webSocketService;
    @Autowired
    public LiveChatController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @PostMapping(
            value = "/send",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('PASSENGER', 'DRIVER')")
    public ResponseEntity<?> sendLiveMessage(@RequestBody @Valid LiveMessageIn liveMessageIn){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        liveMessageIn.setUserId(user.getId());
        webSocketService.notifyAdminAboutLiveChatMessage(liveMessageIn);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Message sent successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/respond",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendLiveResponse(@RequestBody @Valid LiveMessageIn liveMessageIn){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Integer senderId = liveMessageIn.getUserId();
        liveMessageIn.setUserId(user.getId());
        webSocketService.notifyUserAboutLiveChatResponse(senderId, liveMessageIn);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Message sent successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
