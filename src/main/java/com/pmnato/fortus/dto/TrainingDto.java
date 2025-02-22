package com.pmnato.fortus.dto;

import com.pmnato.fortus._enum.DayOfWeek;

import java.time.LocalDate;

public record TrainingDto(
        String description,
        LocalDate date,
        boolean status,
        long durationTimeMillis,
        DayOfWeek dayOfWeek
)implements Dto {}
