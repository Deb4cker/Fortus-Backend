package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.request.TrainingRequest;

public class TrainingValidator extends AbstractValidator<TrainingRequest> {

    public TrainingValidator(TrainingRequest request) {
        super(request);
    }

    @Override
    public boolean isValidCreation() {
        return isValid();
    }

    @Override
    public boolean isValid() {
        return requestIsPresent()
                && descriptionIsValid()
                && dateIsValid()
                && durationIsValid()
                && dayOfWeekIsValid();
    }

    private boolean descriptionIsValid() {
        return executePredicate(
                request.description() != null && !request.description().isBlank(),
                EmptyFieldException.class,
                "description"
        );
    }

    private boolean dateIsValid() {
        return executePredicate(
                request.date() != null,
                EmptyFieldException.class,
                "date"
        );
    }

    private boolean durationIsValid() {
        return executePredicate(
                request.durationTimeMillis() >= 0,
                ValidationException.class,
                "Duration must not be negative."
        );
    }

    private boolean dayOfWeekIsValid() {
        return executePredicate(
                request.dayOfWeek() != null,
                EmptyFieldException.class,
                "dayOfWeek"
        );
    }
}
