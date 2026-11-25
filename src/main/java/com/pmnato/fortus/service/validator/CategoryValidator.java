package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.service.request.CategoryRequest;

public class CategoryValidator extends AbstractValidator<CategoryRequest> {

    public CategoryValidator(CategoryRequest request) {
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
        return nameIsValid();
    }

    private boolean nameIsValid() {
        return check(
                request.name() != null && !request.name().isBlank(),
                "The field name is empty."
        );
    }
}
