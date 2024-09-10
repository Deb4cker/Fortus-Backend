package com.pmnato.fortus.exception.auth_exception;

import com.pmnato.fortus.exception.ApplicationException;

public class AuthException extends ApplicationException {
    public AuthException(String message) {
        super(message);
    }
}
