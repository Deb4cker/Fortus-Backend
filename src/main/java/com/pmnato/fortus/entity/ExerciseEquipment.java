package com.pmnato.fortus.entity;

import static com.pmnato.fortus.commons.constants.JoinColumns.EXERCISE_EQUIPMENT;
import static com.pmnato.fortus.commons.constants.JoinColumns.EQUIPMENT_ID;
import static com.pmnato.fortus.commons.constants.JoinColumns.EXERCISE_ID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = EXERCISE_EQUIPMENT)

public class ExerciseEquipment {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = EXERCISE_ID, nullable = false)
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = EQUIPMENT_ID, nullable = false)
    private Equipment equipment;
}
