package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.service.request.Request;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidator<R extends Request> implements Validator<R> {

    protected R request;
    private final List<String> errors = new ArrayList<>();

    protected AbstractValidator(R request) {
        this.request = request;
    }

    @Override
    public void setRequest(R request) {
        this.request = request;
    }

    @Override
    public List<String> getErrors() {
        return List.copyOf(errors);
    }

    protected void resetErrors() {
        errors.clear();
    }

    protected boolean requestIsPresent() {
        return check(request != null, "Request must not be null.");
    }

    protected boolean check(boolean condition, String error) {
        if (!condition) {
            errors.add(error);
        }
        return condition;
    }
}
