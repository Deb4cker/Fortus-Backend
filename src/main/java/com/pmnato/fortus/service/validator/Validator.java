package com.pmnato.fortus.service.validator;

import java.util.List;

public interface Validator<R> {
    boolean isValidCreation();
    boolean isValid();
    void setRequest(R request);
    List<String> getErrors();
}
