package com.pmnato.fortus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pmnato.fortus._enum.DayOfWeek;

import static com.pmnato.fortus.commons.constants.EntityName.TRAINING;
import static com.pmnato.fortus.commons.constants.EntityName.USER;
import static com.pmnato.fortus.commons.constants.JoinColumns.*;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Training
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private LocalDate date;
    private boolean status;
    private long durationTimeMillis;
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = USER_ID, nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = TRAINING, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingExercise> trainingExercises;

    public Training(String description, LocalDate date, boolean status, long l, DayOfWeek dayOfWeek) {
    }
}
