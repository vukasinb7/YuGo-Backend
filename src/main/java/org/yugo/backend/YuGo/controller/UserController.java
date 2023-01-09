package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yugo.backend.YuGo.dto.*;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.mapper.MessageMapper;
import org.yugo.backend.YuGo.mapper.NoteMapper;
import org.yugo.backend.YuGo.model.*;
import org.yugo.backend.YuGo.service.*;
import org.yugo.backend.YuGo.security.TokenUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;
    private final RideService rideService;
    private final NoteService noteService;
    private final PasswordResetCodeService passwordResetCodeService;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, MessageService messageService,
                          RideService rideService, PasswordResetCodeService passwordResetCodeService, NoteService noteService, TokenUtils tokenUtils, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.messageService = messageService;
        this.rideService = rideService;
        this.noteService = noteService;
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.passwordResetCodeService=passwordResetCodeService;
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserTokenStateOut> createAuthenticationToken(@RequestBody JwtAuthenticationIn authenticationRequest) {
        try{
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User)authentication.getPrincipal();
            String jwt = this.tokenUtils.generateToken(user);
            return ResponseEntity.ok(new UserTokenStateOut(jwt, ""));
        }
        catch (AuthenticationException exception){
            throw new BadRequestException("Wrong username or password!");
        }
    }

    @GetMapping(
            value = "/logout",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity logoutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();
            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        }
        throw new BadRequestException("User is not authenticated!");
    }

    @PutMapping(
            value = "/{id}/changePassword",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody PasswordChangeIn passwordChangeIn) {
        userService.changePassword(id, passwordChangeIn.getOldPassword(), passwordChangeIn.getNewPassword());
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(
            value = "/{id}/resetPassword",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> sendResetPasswordCode(@PathVariable Integer id) {
        userService.sendPasswordResetCode(id);
        return new ResponseEntity<>("Email with reset code has been sent!", HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            value = "/{email}/resetPassword",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> sendResetPasswordCodeEfficient(@PathVariable String email) {
        userService.sendPasswordResetCodeEfficient(email);
        return new ResponseEntity<>("Email with reset code has been sent!", HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            value = "/{id}/resetPassword",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> resetPasswordWithCode(@PathVariable Integer id, @RequestBody PasswordResetIn passwordResetIn) {
        userService.resetPassword(id, passwordResetIn.getNewPassword(), passwordResetIn.getCode());
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            value = "/resetPassword",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> resetPasswordWithCodeEfficient(@RequestBody PasswordResetIn passwordResetIn) {
        userService.resetPasswordEfficient(passwordResetIn.getCode(), passwordResetIn.getNewPassword());
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(
            value = "/{id}/ride",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllUsersOut> getAllUsers(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        Page<User> users = userService.getUsersPage(PageRequest.of(page, size));
        return new ResponseEntity<>(new AllUsersOut(users), HttpStatus.OK);
    }

    @GetMapping(
            value = "{email}/email",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserSimplifiedOut> getUserByEmail(@PathVariable String email){
        UserSimplifiedOut user =new UserSimplifiedOut(userService.getUserByEmail(email));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<AllUserMessagesOut> getUserMessages(@PathVariable Integer id){
        return new ResponseEntity<>(new AllUserMessagesOut(messageService.getUserMessages(id)), HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/message",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    public ResponseEntity<MessageOut> sendMessageToUser(@PathVariable Integer id, @RequestBody MessageIn messageIn){
        User sender = userService.getUser(id);
        User receiver = userService.getUser(messageIn.getReceiverId());
        Ride ride = rideService.get(messageIn.getRideId());
        Message msg = new Message(sender, receiver,
                messageIn.getMessage(), LocalDateTime.now(), messageIn.getType(),
                ride);
        messageService.insert(msg);
        return new ResponseEntity<>(MessageMapper.fromMessagetoDTO(msg), HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}/block",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity blockUser(@PathVariable Integer id){
        userService.blockUser(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("message","User is successfully blocked!");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            value = "/{id}/unblock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity unblockUser(@PathVariable Integer id){
        userService.unblockUser(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("message","User is successfully unblocked!");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoteOut> createNote(@PathVariable Integer id, @RequestBody NoteIn noteIn){
        User user = userService.getUser(id);
        Note note = new Note(user, noteIn.getMessage(), LocalDateTime.now());
        noteService.insert(note);
        return new ResponseEntity<>(NoteMapper.fromNotetoDTO(note), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/note",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AllNotesOut> getUserNotes(@PathVariable Integer id, @RequestParam(name = "page") int page,
                                                    @RequestParam(name = "size") int size){
        Page<Note> notes = noteService.getUserNotes(id, PageRequest.of(page, size));
        return new ResponseEntity<>(new AllNotesOut(notes), HttpStatus.OK);
    }
}
