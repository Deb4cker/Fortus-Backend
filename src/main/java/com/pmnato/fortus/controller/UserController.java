package com.pmnato.fortus.controller;

import java.util.List;

import com.pmnato.fortus.dto.UserDto;
import com.pmnato.fortus.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pmnato.fortus.service.request.UserRequest;
import com.pmnato.fortus.service.request.LoginRequest;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;
import static com.pmnato.fortus.commons.constants.EntityRoutes.USER_ROUTE;

@RestController
@RequestMapping(USER_ROUTE)
public class UserController extends BaseController<UserDto, UserRequest> {

    public UserController(UserService service) {
        super(service);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody UserRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody UserRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserService service = (UserService) this.service;

        var result = service.login(request.email(), request.password());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
