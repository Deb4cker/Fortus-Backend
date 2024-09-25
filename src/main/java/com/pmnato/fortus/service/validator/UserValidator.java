package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.service.request.UserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator implements Validator<UserRequest> {

    private UserRequest request;
    private final UserRepository userRepository;

    @Override
    public boolean isValid() {
        return nameIsValid() && lastNameIsValid() && emailIsValid() && passwordIsValid();
    }

    @Override
    public void setRequest(UserRequest request) {
        this.request = request;
    }

    private boolean nameIsValid() {
        return request.firstName() != null && !request.firstName().trim().isEmpty();
    }

    private boolean lastNameIsValid() {
        return request.lastName() != null && !request.lastName().trim().isEmpty();
    }

    private boolean emailIsValid() {
        return request.email() != null
           &&  !emailAlreadyExists()
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
        return userRepository.findByEmail(request.email()).isEmpty();
    }
}
