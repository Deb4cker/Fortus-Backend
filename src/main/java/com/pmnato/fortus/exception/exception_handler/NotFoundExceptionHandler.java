package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.exception.not_found.NotFoundException;
import com.pmnato.fortus.exception.not_found.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_USER;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotFoundExceptionHandler extends ApplicationExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return treatedResponse(ex, CAPITALIZED_USER + BASE_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return treatedResponse(ex, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause) {
        return getObjectResponseEntity(cause, HttpStatus.NOT_FOUND);
    }

    private static final String BASE_MESSAGE = " does not exists.";
}
