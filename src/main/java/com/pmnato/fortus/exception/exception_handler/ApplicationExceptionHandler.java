package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.service.response.ApiResponse;

import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ApplicationExceptionHandler
{
    protected ResponseEntity<Object> getObjectResponseEntity(
        Object data,
        @NonNull HttpStatus httpStatus
    ) 
    {
        return new ResponseEntity<>(new ApiResponse<>(data), httpStatus);
    }

    protected abstract ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause);
}
