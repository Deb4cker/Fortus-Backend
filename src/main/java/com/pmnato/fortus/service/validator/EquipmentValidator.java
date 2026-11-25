package com.pmnato.fortus.service.validator;

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
        resetErrors();
        if (!requestIsPresent()) return false;
        boolean valid = nameIsValid();
        valid &= weightIsValid();
        return valid;
    }

    private boolean nameIsValid() {
        return check(
                request.name() != null && !request.name().isBlank(),
                "The field name is empty."
        );
    }

    private boolean weightIsValid() {
        return check(
                Double.isFinite(request.weight()) && request.weight() >= 0,
                "Weight must be a finite, non-negative number."
        );
    }
}
