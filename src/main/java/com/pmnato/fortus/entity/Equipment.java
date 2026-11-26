package com.pmnato.fortus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Equipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double weight;
    private String imageUrl;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseEquipment> exerciseEquipments;

    public Equipment(Long id, String name, double weight, String imageUrl) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.exerciseEquipments = new ArrayList<>();
    }
}
