package com.pmnato.fortus.service.validator;

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
        resetErrors();
        if (!requestIsPresent()) return false;
        boolean valid = descriptionIsValid();
        valid &= dateIsValid();
        valid &= durationIsValid();
        valid &= dayOfWeekIsValid();
        return valid;
    }

    private boolean descriptionIsValid() {
        return check(
                request.description() != null && !request.description().isBlank(),
                "The field description is empty."
        );
    }

    private boolean dateIsValid() {
        return check(
                request.date() != null,
                "The field date is empty."
        );
    }

    private boolean durationIsValid() {
        return check(
                request.durationTimeMillis() >= 0,
                "Duration must not be negative."
        );
    }

    private boolean dayOfWeekIsValid() {
        return check(
                request.dayOfWeek() != null,
                "The field dayOfWeek is empty."
        );
    }
}
