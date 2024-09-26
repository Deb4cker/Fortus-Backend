package com.pmnato.fortus.exception.validation.user;

import com.pmnato.fortus.exception.validation.ValidationException;

public class InvalidNameException extends ValidationException {
    public InvalidNameException() {
        super("Invalid name.");
    }
}
