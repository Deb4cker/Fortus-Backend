package com.pmnato.fortus.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Equipment {
    private long id;
    private String name;
    private double weight;
    private String imageUrl;
}
