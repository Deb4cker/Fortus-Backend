package com.pmnato.fortus.service.request;

public record UserRequest(
        String firstName,
        String midName,
        String lastName,
        String email,
        String password,
        String passwordConfirmation
) implements Request {}
