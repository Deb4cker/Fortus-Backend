package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.auth_exception.AuthException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.exception.validation.user.EmailAlreadyInUseException;
import com.pmnato.fortus.exception.validation.user.InvalidEmailException;
import com.pmnato.fortus.exception.validation.user.InvalidNameException;
import com.pmnato.fortus.exception.validation.user.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserValidationExceptionHandler extends ApplicationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleAuthException(AuthException ex) {
        return treatedResponse(ex, "Validation Failed.");
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyInUseException ex) {
        return treatedResponse(ex, "Invalid email address.");
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex) {
        return treatedResponse(ex, "Invalid email address.");
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<Object> handleInvalidNameException(InvalidNameException ex) {
        return treatedResponse(ex, "Invalid name.");
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException ex) {
        return treatedResponse(ex, "Invalid password.");
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(
                exception.getMessage(),
                cause,
                HttpStatus.FORBIDDEN);
    }
}
