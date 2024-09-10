package com.pmnato.fortus.exception.security_error;

import com.pmnato.fortus.exception.ApplicationException;

public class SecurityException extends ApplicationException {
    public SecurityException(String msg) {
        super(msg);
    }
}
