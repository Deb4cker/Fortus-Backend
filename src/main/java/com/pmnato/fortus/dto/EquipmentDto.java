package com.pmnato.fortus.dto;

public record EquipmentDto(
        long id,
        String name,
        double weight,
        String imageUrl
)implements Dto{}
