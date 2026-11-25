package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
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
        return requestIsPresent()
                && nameIsValid()
                && repetitionsAreValid()
                && difficultyIsValid()
                && categoryIdIsValid();
    }

    private boolean nameIsValid() {
        return executePredicate(
                request.name() != null && !request.name().isBlank(),
                EmptyFieldException.class,
                "name"
        );
    }

    private boolean repetitionsAreValid() {
        return executePredicate(
                request.repetitions() > 0,
                ValidationException.class,
                "Repetitions must be greater than zero."
        );
    }

    private boolean difficultyIsValid() {
        return executePredicate(
                request.difficultyLevel() != null,
                EmptyFieldException.class,
                "difficultyLevel"
        );
    }

    private boolean categoryIdIsValid() {
        return executePredicate(
                request.categoryId() != null && request.categoryId() > 0,
                ValidationException.class,
                "A valid categoryId is required."
        );
    }
}
