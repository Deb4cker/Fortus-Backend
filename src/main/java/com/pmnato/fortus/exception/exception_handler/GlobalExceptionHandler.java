package com.pmnato.fortus.exception.exception_handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pmnato.fortus.service.response.ApiResponse;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        return response("Null parameter on args.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception exception) {
        return response("Server died", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> response(Object data, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(data), status);
    }
}
