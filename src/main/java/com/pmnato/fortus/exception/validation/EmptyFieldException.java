package com.pmnato.fortus.exception.validation;

public class EmptyFieldException extends ValidationException {
    public EmptyFieldException(String fieldName) {
        super("The field " + fieldName + " is empty.");
    }
}
