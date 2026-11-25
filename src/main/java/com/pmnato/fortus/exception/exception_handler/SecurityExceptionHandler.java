package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.security.SecurityException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler extends ApplicationExceptionHandler
{
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleSecurityException(SecurityException ex) {
        return treatedResponse(ex, "Some error occurred");
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(cause, HttpStatus.FORBIDDEN);
    }
}
