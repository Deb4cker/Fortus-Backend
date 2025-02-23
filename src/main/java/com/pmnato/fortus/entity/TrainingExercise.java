package com.pmnato.fortus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pmnato.fortus.commons.constants.JoinColumns.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TRAINING_EXERCISE)
public class TrainingExercise
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = TRAINING_ID, nullable = false)
    private Training training;

    @ManyToOne
    @JoinColumn(name = EXERCISE_ID, nullable = false)
    private Exercise exercise;
}