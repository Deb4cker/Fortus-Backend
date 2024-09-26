package com.pmnato.fortus.exception.validation.user;

import com.pmnato.fortus.exception.validation.ValidationException;

public class EmailAlreadyExists extends ValidationException {
    public EmailAlreadyExists() {
        super("The provided email is already in use.");
    }
}
