package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.request.EquipmentRequest;

public class EquipmentValidator extends AbstractValidator<EquipmentRequest> {

    public EquipmentValidator(EquipmentRequest request) {
        super(request);
    }

    @Override
    public boolean isValidCreation() {
        return isValid();
    }

    @Override
    public boolean isValid() {
        return requestIsPresent() && nameIsValid() && weightIsValid();
    }

    private boolean nameIsValid() {
        return executePredicate(
                request.name() != null && !request.name().isBlank(),
                EmptyFieldException.class,
                "name"
        );
    }

    private boolean weightIsValid() {
        return executePredicate(
                Double.isFinite(request.weight()) && request.weight() >= 0,
                ValidationException.class,
                "Weight must be a finite, non-negative number."
        );
    }
}
