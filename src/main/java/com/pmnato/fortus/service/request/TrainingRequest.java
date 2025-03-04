package com.pmnato.fortus.service.request;

import com.pmnato.fortus._enum.DayOfWeek;
import java.time.LocalDate;

public record TrainingRequest(
        Long id,
        String description,
        LocalDate date,
        boolean status,
        long durationTimeMillis,
        DayOfWeek dayOfWeek
)implements Request {}