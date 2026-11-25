package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.service.request.ExerciseRequest;

public class ExerciseValidator extends AbstractValidator<ExerciseRequest> {

    public ExerciseValidator(ExerciseRequest request) {
        super(request);
    }

    @Override
    public boolean isValidCreation() {
        return isValid();
    }

    @Override
    public boolean isValid() {
        resetErrors();
        if (!requestIsPresent()) return false;
        boolean valid = nameIsValid();
        valid &= repetitionsAreValid();
        valid &= difficultyIsValid();
        valid &= categoryIdIsValid();
        return valid;
    }

    private boolean nameIsValid() {
        return check(
                request.name() != null && !request.name().isBlank(),
                "The field name is empty."
        );
    }

    private boolean repetitionsAreValid() {
        return check(
                request.repetitions() > 0,
                "Repetitions must be greater than zero."
        );
    }

    private boolean difficultyIsValid() {
        return check(
                request.difficultyLevel() != null,
                "The field difficultyLevel is empty."
        );
    }

    private boolean categoryIdIsValid() {
        return check(
                request.categoryId() != null && request.categoryId() > 0,
                "A valid categoryId is required."
        );
    }
}
