package com.pmnato.fortus.service.request;

public record CategoryRequest (
        String name,
        String imageUrl
) implements Request {}
