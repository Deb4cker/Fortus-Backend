package com.pmnato.fortus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pmnato.fortus.commons.constants.JoinColumns.EXERCISE_ID;

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

    @ManyToOne
    @JoinColumn(name = EXERCISE_ID, nullable = false)
    private Exercise exercise;

    public Equipment(Long id, String name, double weight, String s) {
    }
}
