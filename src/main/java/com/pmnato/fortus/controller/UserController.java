package com.pmnato.fortus.controller;

import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
