package com.pmnato.fortus.exception.validation;

import com.pmnato.fortus.exception.ApplicationException;

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super("One or many Validation Error(s) Occurred.");
    }
}
