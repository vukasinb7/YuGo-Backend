package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.MessageResponseMapper;
import org.yugo.backend.YuGo.mapper.NoteResponseMapper;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;
    private final RideService rideService;
    private final NoteService noteService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, MessageServiceImpl messageServiceImpl,
                          RideServiceImpl rideServiceImpl, NoteServiceImpl noteServiceImpl){
        this.userService = userServiceImpl;
        this.messageService = messageServiceImpl;
        this.rideService = rideServiceImpl;
        this.noteService = noteServiceImpl;
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AllRidesResponse> getUserRides(@PathVariable Integer id, @RequestParam int page,
                                                       @RequestParam int size, @RequestParam String sort,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Page<Ride> rides = rideService.getUserRides(id, from, to,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort)));

        return new ResponseEntity<>(new AllRidesResponse(rides.stream()), HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersResponse> getAllUsers(@RequestParam int page, @RequestParam int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersResponse(users.get()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(new LoginResponse(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUserMessagesResponse> getUserMessages(@PathVariable Integer id){
        return new ResponseEntity<>(new AllUserMessagesResponse(messageService.getUserMessages(id).stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MessageResponse> sendUserMessages(@PathVariable Integer id, @RequestParam Integer receiverId, @RequestBody MessageRequest messageRequest){
        Message msg = new Message(userService.getUser(id).get(), userService.getUser(receiverId).get(),
                messageRequest.getMessage(), LocalDateTime.now(), messageRequest.getType(),
                null);
        messageService.save(msg);
        return new ResponseEntity<>(MessageResponseMapper.fromMessagetoDTO(msg), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/block",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity blockUser(@PathVariable Integer id){
        userService.blockUser(id);
        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity unblockUser(@PathVariable Integer id){
        userService.unblockUser(id);
        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NoteResponse> createNote(@PathVariable Integer id, @RequestBody NoteRequest noteRequest){
        Note note = new Note(userService.getUser(id).get(), noteRequest.getMessage(), LocalDateTime.now());
        noteService.save(note);
        return new ResponseEntity<>(NoteResponseMapper.fromNotetoDTO(note), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllNotesResponse> getUserNotes(@PathVariable Integer id, @RequestParam int page, @RequestParam int size){
        Page<Note> notes = noteService.getUserNotes(id, PageRequest.of(page, size));
        return new ResponseEntity<>(new AllNotesResponse(notes.get()), HttpStatus.OK);
    }
}
