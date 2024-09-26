package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler extends ApplicationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return treatedResponse(ex, "Some field is incorrect.");
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Object> handleEmptyFieldException(EmptyFieldException ex) {
        return treatedResponse(ex, "Invalid Action");
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(
                exception.getMessage(),
                cause,
                HttpStatus.FORBIDDEN);
    }
}
