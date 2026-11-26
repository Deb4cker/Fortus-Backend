package com.pmnato.fortus.service.request;

import com.pmnato.fortus._enum.Difficulty;

import java.util.List;

public record ExerciseRequest (
        String name,
        int repetitions,
        Difficulty difficultyLevel,
        String suggestedTime,
        String imageUrl,
        String VideoUrl,
        Long categoryId,
        List<Long> equipmentIds
) implements Request{}
