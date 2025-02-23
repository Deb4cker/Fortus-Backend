package com.pmnato.fortus.entity;

import com.pmnato.fortus._enum.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityName.EXERCISE;
import static com.pmnato.fortus.commons.constants.JoinColumns.CATEGORY_ID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Exercise
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Difficulty difficultyLevel;
    private int repetitions;
    private String suggestedTime;
    private String imageUrl;
    private String videoUrl;

    @OneToMany(mappedBy = EXERCISE, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingExercise> trainingExercises;

    @OneToMany(mappedBy = EXERCISE, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipments;

    @ManyToOne
    @JoinColumn(name = CATEGORY_ID)
    private Category category;

    public Exercise(Long id, String name, Difficulty difficulty, int repetitions, String s, String s1, String s2) {
    }
}
