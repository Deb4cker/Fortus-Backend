package com.pmnato.fortus.entity;

import com.pmnato.fortus._enum.Difficulty;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Exercise {
    private Long id;

    private String name;

    private Difficulty difficultyLevel;

    private int repetitions;

    private String suggestedTime;

    private String imageUrl;

    private String VideoUrl;

}
