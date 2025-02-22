package com.pmnato.fortus.exception.auth_exception;

public class PasswordDoesNotMatchException extends AuthException {
    public PasswordDoesNotMatchException() {
        super("Password doesn't match!");
    }
}
