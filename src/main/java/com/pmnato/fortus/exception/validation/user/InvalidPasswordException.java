package com.pmnato.fortus.exception.validation.user;

import com.pmnato.fortus.exception.validation.ValidationException;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException() {
        super("Invalid Password.");
    }
}
