package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler extends ApplicationExceptionHandler
{
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return treatedResponse(ex, ex.getErrors());
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Object> handleEmptyFieldException(EmptyFieldException ex) {
        return treatedResponse(ex, ex.getErrors());
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(cause, HttpStatus.FORBIDDEN);
    }
}
