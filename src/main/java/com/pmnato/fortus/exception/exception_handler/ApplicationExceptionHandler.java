package com.pmnato.fortus.exception.exception_handler;

import com.pmnato.fortus.exception.ApplicationException;
import com.pmnato.fortus.service.response.ApiErrorResponse;

import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ApplicationExceptionHandler
{
    protected ResponseEntity<Object> getObjectResponseEntity(
        Object data,
        @NonNull HttpStatus httpStatus
    ) 
    {
        List<String> errors = data instanceof List<?> values
                ? values.stream().map(String::valueOf).toList()
                : List.of();
        String detail = errors.isEmpty() ? String.valueOf(data) : "Um ou mais campos são inválidos.";
        String code = switch (httpStatus) {
            case BAD_REQUEST -> "VALIDATION_ERROR";
            case UNAUTHORIZED -> "AUTHENTICATION_ERROR";
            case FORBIDDEN -> "ACCESS_DENIED";
            case NOT_FOUND -> "NOT_FOUND";
            default -> "REQUEST_ERROR";
        };
        return new ResponseEntity<>(
                ApiErrorResponse.of(httpStatus, detail, code, errors),
                httpStatus
        );
    }

    protected abstract ResponseEntity<Object> treatedResponse(ApplicationException exception, Object cause);
}
