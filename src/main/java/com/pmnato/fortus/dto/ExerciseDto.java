package com.pmnato.fortus.dto;

import com.pmnato.fortus._enum.Difficulty;

public record ExerciseDto(
        Long id,
        String name,
        int repetitions,
        Difficulty difficultyLevel,
        String suggestedTime,
        String imageUrl,
        String VideoUrl
) implements Dto {}