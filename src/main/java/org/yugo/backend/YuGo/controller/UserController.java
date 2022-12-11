package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.yugo.backend.YuGo.service.MessageService;
import org.yugo.backend.YuGo.service.NoteService;
import org.yugo.backend.YuGo.service.RideService;
import org.yugo.backend.YuGo.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    ResponseEntity<AllRidesOut> getUserRides(@PathVariable Integer id, @RequestParam(name = "page") int page,
                                             @RequestParam(name = "size") int size, @RequestParam(name = "sort") String sort,
                                             @RequestParam(name = "from") String from,
                                             @RequestParam(name = "to") String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fromTime = LocalDate.parse(from, formatter).atTime(LocalTime.MIDNIGHT);
        LocalDateTime toTime = LocalDate.parse(to, formatter).atTime(LocalTime.MIDNIGHT);
        Page<Ride> rides = rideService.getUserRides(id, fromTime, toTime,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort)));

        return new ResponseEntity<>(new AllRidesOut(rides), HttpStatus.OK);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllUsersOut> getAllUsers(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(users), HttpStatus.OK);
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
        return new ResponseEntity<>(new AllUserMessagesOut(messageService.getUserMessages(id)), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MessageOut> sendMessageToUser(@PathVariable Integer id, @RequestBody MessageIn messageIn){
        Optional<User> senderOpt = userService.getUser(2);
        Optional<User> receiverOpt = userService.getUser(id);
        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            Message msg = new Message(senderOpt.get(), receiverOpt.get(),
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
    public ResponseEntity<Void> blockUser(@PathVariable Integer id){
        if (userService.blockUser(id)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> unblockUser(@PathVariable Integer id){
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
    public ResponseEntity<AllNotesOut> getUserNotes(@PathVariable Integer id, @RequestParam(name = "page") int page,
                                                    @RequestParam(name = "size") int size){
        Page<Note> notes = noteService.getUserNotes(id, PageRequest.of(page, size));
        return new ResponseEntity<>(new AllNotesOut(notes), HttpStatus.OK);
    }
}