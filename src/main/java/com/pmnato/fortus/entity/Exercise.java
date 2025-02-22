package com.pmnato.fortus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pmnato.fortus._enum.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pmnato.fortus.commons.constants.JoinColumns.TRAINING_ID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Difficulty difficultyLevel;
    private int repetitions;
    private String suggestedTime;
    private String imageUrl;
    private String VideoUrl;

    @ManyToOne
    @JoinColumn(name = TRAINING_ID, nullable = false)
    @JsonBackReference
    private Training training;

    public Exercise(Long id, String name, Difficulty difficulty, int repetitions, String s, String s1, String s2) {
    }
}
