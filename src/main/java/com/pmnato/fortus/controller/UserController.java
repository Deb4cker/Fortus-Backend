package com.pmnato.fortus.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import com.pmnato.fortus.dto.UserDto;
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
public class UserController extends BaseController<UserDto, UserRequest> {

    private final UserService userService;

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody UserRequest request) {
        Long id = userService.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody UserRequest request) {
        userService.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        var result = userService.login(request.email(), request.password());
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
