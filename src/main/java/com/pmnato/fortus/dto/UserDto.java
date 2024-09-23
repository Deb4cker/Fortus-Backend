package com.pmnato.fortus.dto;

public record UserDto(
        Long id,
        String name,
        String email
) implements Dto{}
