package com.pmnato.fortus.service.request;

public record EquipmentRequest(
        String name,
        double weight,
        String imageUrl
)implements Request {}