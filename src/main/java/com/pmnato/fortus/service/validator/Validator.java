package com.pmnato.fortus.service.validator;

public interface Validator<R> {
    boolean isValid();
    void setRequest(R request);
}
