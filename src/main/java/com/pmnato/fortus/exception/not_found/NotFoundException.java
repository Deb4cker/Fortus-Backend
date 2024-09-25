package com.pmnato.fortus.exception.not_found;

import com.pmnato.fortus.exception.ApplicationException;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
