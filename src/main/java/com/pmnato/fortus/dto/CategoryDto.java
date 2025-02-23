package com.pmnato.fortus.dto;

public record CategoryDto(
        Long id,
        String name,
        String imageUrl
) implements Dto {}
