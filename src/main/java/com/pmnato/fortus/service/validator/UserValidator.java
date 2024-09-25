package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.service.request.UserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator implements Validator<UserRequest> {

    private UserRequest request;
    private final UserRepository userRepository;

    @Override
    public boolean isValidCreation() {
        return isValid() && !emailAlreadyExists();
    }

    @Override
    public boolean isValid() {
        return nameIsValid(request.firstName()) && nameIsValid(request.lastName())  && emailIsValid() && passwordIsValid();
    }

    @Override
    public void setRequest(UserRequest request) {
        this.request = request;
    }

    private boolean nameIsValid(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean emailIsValid() {
        return request.email() != null
           && !request.email().trim().isEmpty()
           &&  request.email().contains("@");
    }

    private boolean passwordIsValid() {
        return request.password() != null
           &&  request.password().equals(request.passwordConfirmation())
           && !request.password().trim().isEmpty()
           &&  request.password().length() >= 6;
    }

    private boolean emailAlreadyExists() {
        return userRepository.findByEmail(request.email()).isPresent();
    }
}
