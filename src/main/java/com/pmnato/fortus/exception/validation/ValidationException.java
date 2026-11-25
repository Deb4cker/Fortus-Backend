package com.pmnato.fortus.exception.validation;

import com.pmnato.fortus.exception.ApplicationException;

import java.util.List;

public class ValidationException extends ApplicationException {
    private final List<String> errors;

    public ValidationException(String message) {
        this(List.of(message));
    }

    public ValidationException(List<String> errors) {
        super(String.join("; ", errors));
        this.errors = List.copyOf(errors);
    }

    public ValidationException() {
        this(List.of("One or many Validation Error(s) Occurred."));
    }

    public List<String> getErrors() {
        return errors;
    }
}
