package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;

import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public abstract class ApplicationExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRunTimeException (Exception exception) {
        return getObjectResponseEntity(
                exception.getMessage(),
                "Server died",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException (NullPointerException exception){
        return getObjectResponseEntity(exception.getMessage(), 
        "Null parameter on args.", 
        HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> getObjectResponseEntity(
        String message, 
        Object cause, 
        @NonNull HttpStatus httpStatus
    ) 
    {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        body.put("returned", cause);
        body.put("time", new Date().toString());

        return new ResponseEntity<>(body, httpStatus);
    }

    protected abstract ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause);
}
