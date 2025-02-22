package com.pmnato.fortus.exception.not_found;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_EXERCISE;

public class ExerciseNotFoundException extends NotFoundException {
    public ExerciseNotFoundException() {
        super(CAPITALIZED_EXERCISE);
    }
}