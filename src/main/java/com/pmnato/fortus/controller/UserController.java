package com.pmnato.fortus.controller;

import java.util.List;

import com.pmnato.fortus.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import com.pmnato.fortus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pmnato.fortus.service.request.UserRequest;
import com.pmnato.fortus.service.request.LoginRequest;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;
import static com.pmnato.fortus.commons.constants.EntityRoutes.USER_ROUTE;

@RestController
@AllArgsConstructor
@RequestMapping(USER_ROUTE)
public class UserController {

    private final UserService userService;

    @PostMapping(CREATE)
    public ResponseEntity<Void> create(@RequestBody UserRequest request) {
        userService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(ALL)
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody UserRequest request) {
        userService.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        userService.login(request.email(), request.password());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
