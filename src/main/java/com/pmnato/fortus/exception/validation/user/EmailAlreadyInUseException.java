package com.pmnato.fortus.exception.validation.user;

import com.pmnato.fortus.exception.validation.ValidationException;

public class EmailAlreadyInUseException extends ValidationException {
    public EmailAlreadyInUseException() {
        super("The provided email is already in use.");
    }
}
