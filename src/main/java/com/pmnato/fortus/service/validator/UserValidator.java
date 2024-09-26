package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.user.EmailAlreadyInUseException;
import com.pmnato.fortus.exception.validation.user.InvalidEmailException;
import com.pmnato.fortus.exception.validation.user.InvalidNameException;
import com.pmnato.fortus.exception.validation.user.InvalidPasswordException;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.service.request.UserRequest;

public class UserValidator extends AbstractValidator<UserRequest> {

    private final UserRepository userRepository;

    public UserValidator(UserRequest request, UserRepository userRepository) {
        super(request);
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValidCreation() {
        return isValid() && !emailAlreadyInUse();
    }

    @Override
    public boolean isValid() {
        return nameIsValid(request.firstName()) && nameIsValid(request.lastName())  && emailIsValid() && passwordIsValid();
    }

    private boolean nameIsValid(String name) {
        return executePredicate(name != null && !name.trim().isEmpty(), InvalidNameException.class);
    }

    private boolean emailIsValid() {
        return executePredicate(request.email() != null
                && !request.email().trim().isEmpty()
                &&  request.email().contains("@"),
                InvalidEmailException.class
        );
    }

    private boolean passwordIsValid() {
        return executePredicate(request.password() != null
                &&  request.password().equals(request.passwordConfirmation())
                && !request.password().trim().isEmpty()
                &&  request.password().length() >= 6,
                InvalidPasswordException.class
        );
    }

    private boolean emailAlreadyInUse() {
        boolean exists = userRepository.findByEmail(request.email()).isPresent();
        return executePredicate(!exists, EmailAlreadyInUseException.class);
    }
}
