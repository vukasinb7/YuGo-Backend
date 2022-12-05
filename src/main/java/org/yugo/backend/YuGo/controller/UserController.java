package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.mapper.MessageMapper;
import org.yugo.backend.YuGo.mapper.NoteMapper;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;
    private final RideService rideService;
    private final NoteService noteService;

    @Autowired
    public UserController(UserService userService, MessageService messageService,
                          RideService rideService, NoteService noteService){
        this.userService = userService;
        this.messageService = messageService;
        this.rideService = rideService;
        this.noteService = noteService;
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AllRidesOut> getUserRides(@PathVariable Integer id, @RequestParam int page,
                                             @RequestParam int size, @RequestParam String sort,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Page<Ride> rides = rideService.getUserRides(id, from, to,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort)));

        return new ResponseEntity<>(new AllRidesOut(rides.stream()), HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersOut> getAllUsers(@RequestParam int page, @RequestParam int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(users.get()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginOut> loginUser(@RequestBody LoginIn loginIn){
        userService.authenticateUser(loginIn.getEmail(), loginIn.getPassword());
        return new ResponseEntity<>(new LoginOut(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUserMessagesOut> getUserMessages(@PathVariable Integer id){
        return new ResponseEntity<>(new AllUserMessagesOut(messageService.getUserMessages(id).stream()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MessageOut> sendMessageToUser(@PathVariable Integer id, @RequestParam Integer senderId, @RequestBody MessageIn messageIn){
        Optional<User> sender = userService.getUser(senderId);
        Optional<User> receiver = userService.getUser(id);
        if (sender.isPresent() && receiver.isPresent()) {
            Message msg = new Message(sender.get(), receiver.get(),
                    messageIn.getMessage(), LocalDateTime.now(), messageIn.getType(),
                    null);
            messageService.insert(msg);
            return new ResponseEntity<>(MessageMapper.fromMessagetoDTO(msg), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(
            value = "/{id}/block",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity blockUser(@PathVariable Integer id){
        if (userService.blockUser(id)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity unblockUser(@PathVariable Integer id){
        if (userService.unblockUser(id)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NoteOut> createNote(@PathVariable Integer id, @RequestBody NoteIn noteIn){
        Optional<User> userOpt = userService.getUser(id);
        if (userOpt.isPresent()){
            Note note = new Note(userOpt.get(), noteIn.getMessage(), LocalDateTime.now());
            noteService.insert(note);
            return new ResponseEntity<>(NoteMapper.fromNotetoDTO(note), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllNotesOut> getUserNotes(@PathVariable Integer id, @RequestParam int page, @RequestParam int size){
        Page<Note> notes = noteService.getUserNotes(id, PageRequest.of(page, size));
        return new ResponseEntity<>(new AllNotesOut(notes.get()), HttpStatus.OK);
    }
}
