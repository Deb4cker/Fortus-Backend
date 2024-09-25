package com.pmnato.fortus.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pmnato.fortus.dto.UserDto;
import com.pmnato.fortus.entity.User;
import com.pmnato.fortus.exception.not_found.UserNotFoundException;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.service.request.UserRequest;
import com.pmnato.fortus.service.validator.UserValidator;
import com.pmnato.fortus.utils.EncryptedInputData;
import com.pmnato.fortus.utils.Encryptor;
import com.pmnato.fortus.utils.PasswordChecker;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserDto findById(Long id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        return mapToDto(user);
    }

    public Long save(UserRequest request) {
        var validator = new UserValidator(repository);
        validator.setRequest(request);
        boolean valid = validator.isValidCreation();

        if (valid) {
            User user = constructUser(request);
            user = repository.save(user);
            logger.info("{} {} registered in the system. (id={})", user.getFirstName(), user.getLastName(), user.getId());
            return user.getId();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void update(Long id, UserRequest request){
        var validator = new UserValidator(repository);
        validator.setRequest(request);
        boolean valid = validator.isValid();
        if (valid) {
            User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
            setData(user, request);
            repository.save(user);
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        boolean isPasswordCorrect = PasswordChecker.isCorrectPassword(user, password);

        if(isPasswordCorrect) {
            logger.info("{} {} (id={}) logged.", user.getFirstName(), user.getLastName(), user.getId());
            return mapToDto(user);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login fail!");
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getMidName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    private void setData(User user, UserRequest request){
        EncryptedInputData secretData = Encryptor.getEncryptData(request.password());
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setMidName(request.midName());
        user.setFirstName(request.lastName());
        user.setPassword(secretData.hashedPassword());
        user.setSalt(secretData.salt());
    }

    private User constructUser(UserRequest request) {
        var encryptData = Encryptor.getEncryptData(request.password());
        return new User(
                null,
                request.firstName(),
                request.midName(),
                request.lastName(),
                request.email(),
                encryptData.hashedPassword(),
                encryptData.salt(),
                new ArrayList<>()
        );
    }
}


