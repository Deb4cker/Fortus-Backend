package com.pmnato.fortus.service.request;

import com.pmnato.fortus._enum.Difficulty;

public record ExerciseRequest (
        String name,
        int repetitions,
        Difficulty difficultyLevel,
        String suggestedTime,
        String imageUrl,
        String VideoUrl,
        Long categoryId
) implements Request{}
