package com.pmnato.fortus.service.request;

public record EquipmentRequest(
        long id,
        String name,
        double weight,
        String imageUrl
)implements Request {}