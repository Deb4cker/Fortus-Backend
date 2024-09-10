package com.pmnato.fortus.controller;

import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.exception.not_found.UserNotFoundException;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.utils.PasswordChecker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    @GetMapping("/all") // localhost:8080/user/all
    public User[] getAll() {
        return new User[]{
                new User(1L, "John", "john@email.com", "", ""),
                new User(2L, "Mary", "mary@email.com", "", ""),
                new User(3L, "Paul", "paul@email.com", "", ""),
                new User(4L, "Jesy", "jesy@email.com", "", ""),
                new User(5L, "Kali", "kali@email.com", "", ""),
                new User(6L, "Beny", "beny@email.com", "", ""),
                new User(7L, "Bill", "bill@email.com", "", ""),
                new User(8L, "Alle", "alle@email.com", "", ""),
                new User(9L, "Vlad", "vlad@email.com", "", "")
        };
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginTest(@RequestBody UserLoginDto data) {
        User user = repository.//findByEmail(data.email()).orElseThrow(UserNotFoundException::new);
                                findById(1L).orElseThrow(UserNotFoundException::new);

        final boolean isPasswordCorrect = new PasswordChecker().isCorrectPassword(user, data.password());
        if(isPasswordCorrect) {
            return new ResponseEntity<>("Login Efetuado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Login ou Senha incorreta", HttpStatus.UNAUTHORIZED);
    }

    private record UserLoginDto(String email, String password){}
}


