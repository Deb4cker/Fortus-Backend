package com.pmnato.fortus.service.response;

public record Metadata(
    int page, 
    int size, 
    int totalItems, 
    int totalPages
){ }
