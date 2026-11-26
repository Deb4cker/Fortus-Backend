package com.pmnato.fortus.exception.exception_handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pmnato.fortus.service.response.ApiErrorResponse;

import lombok.NonNull;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        return response(
                "Um ou mais campos são inválidos.",
                "BAD_REQUEST",
                HttpStatus.BAD_REQUEST,
                List.of("Null parameter on args.")
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception exception) {
        return response(
                "Ocorreu um erro inesperado.",
                "INTERNAL_SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of()
        );
    }

    private ResponseEntity<Object> response(String detail, String code, @NonNull HttpStatus status, List<String> errors) {
        return new ResponseEntity<>(ApiErrorResponse.of(status, detail, code, errors), status);
    }
}
