package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.auth_exception.AuthException;
import com.pmnato.fortus.exception.auth_exception.PasswordDoesNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler extends ApplicationExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuthException(AuthException ex) {
        return treatedResponse(ex, "Authentication Failed");
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<Object> handlePasswordDoesNotMatchException(PasswordDoesNotMatchException ex) {
        return treatedResponse(ex, "Password Does Not Match");
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(
                exception.getMessage(),
                cause,
                HttpStatus.UNAUTHORIZED);
    }
}
