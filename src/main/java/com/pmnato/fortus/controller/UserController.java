package com.pmnato.fortus.controller;

import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.exception.not_found.UserNotFoundException;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.utils.PasswordChecker;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/all") // localhost:8080/user/all
    public ResponseEntity<List<User>> getAll() {
        var users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginTest(@RequestBody UserLoginDto data) {
        String message = "Login ou Senha incorreta";
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        User user = repository.findByEmail(data.email()).orElse(returnMockUser());

        final boolean isPasswordCorrect = PasswordChecker.isCorrectPassword(user, data.password());
        if(isPasswordCorrect) {
            logger.info("{} logged", user.getName());
            message = "Login Efetuado!"; status = HttpStatus.OK;
        }

        return new ResponseEntity<>(message, status);
    }

    private User returnMockUser(){
        logger.info("Failed to find user by email. Returned the mock user.");
        return new User(100L, "John", "johnzin@email.com", "senha_foda", "");
    }

    private record UserLoginDto(String email, String password){}
}


