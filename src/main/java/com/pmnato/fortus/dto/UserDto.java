package com.pmnato.fortus.dto;

public record UserDto(
        Long id,
        String firstName,
        String midName,
        String lastName,
        String email
) implements Dto{}
