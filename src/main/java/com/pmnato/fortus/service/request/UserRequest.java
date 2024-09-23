package com.pmnato.fortus.service.request;

public record UserRequest(
        Long   id,
        String name,
        String email,
        String password,
        String passwordConfirmation
) implements Request {}
