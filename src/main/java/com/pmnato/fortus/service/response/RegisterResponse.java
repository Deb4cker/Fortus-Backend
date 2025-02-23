package com.pmnato.fortus.service.response;

public record RegisterResponse(
        Long id,
        String name,
        String email
) implements Response { }
