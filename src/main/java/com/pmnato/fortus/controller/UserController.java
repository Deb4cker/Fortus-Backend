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
public class UserController extends BaseController<UserDto, UserRequest>
{
    public UserController(UserService service) { super(service); }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody UserRequest request) {
        return super.create(request);
    }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<UserDto>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<UserDto> edit(@PathVariable Long id, @RequestBody UserRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserService service = (UserService) this.service;

        var result = service.login(request.email(), request.password());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
