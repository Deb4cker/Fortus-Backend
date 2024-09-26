package com.pmnato.fortus.exception.validation.user;

import com.pmnato.fortus.exception.validation.ValidationException;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException() {
        super("Invalid email address.");
    }
}
