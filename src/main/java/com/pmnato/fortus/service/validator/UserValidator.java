package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.user.EmailAlreadyInUseException;
import com.pmnato.fortus.exception.validation.user.InvalidEmailException;
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
        boolean valid = isValid();
        if (request != null && hasValidEmailFormat(request.email())) {
            valid &= emailIsAvailable();
        }
        return valid;
    }

    @Override
    public boolean isValid() {
        resetErrors();
        if (!requestIsPresent()) return false;
        boolean valid = nameIsValid(request.firstName(), "firstName");
        valid &= nameIsValid(request.lastName(), "lastName");
        valid &= emailIsValid();
        valid &= passwordIsValid();
        return valid;
    }

    private boolean nameIsValid(String name, String field) {
        return check(
                name != null && !name.isBlank(),
                "The field " + field + " is empty."
        );
    }

    private boolean emailIsValid() {
        return check(hasValidEmailFormat(request.email()), new InvalidEmailException().getMessage());
    }

    private boolean passwordIsValid() {
        return check(
                request.password() != null
                        && request.password().equals(request.passwordConfirmation())
                        && !request.password().isBlank()
                        && request.password().length() >= 6,
                new InvalidPasswordException().getMessage()
        );
    }

    private boolean emailIsAvailable() {
        boolean available = userRepository.findByEmail(request.email()).isEmpty();
        return check(available, new EmailAlreadyInUseException().getMessage());
    }

    private boolean hasValidEmailFormat(String email) {
        return email != null && !email.isBlank() && email.contains("@");
    }
}
