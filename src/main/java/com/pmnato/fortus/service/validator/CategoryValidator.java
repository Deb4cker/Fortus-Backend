package com.pmnato.fortus.service.validator;

import com.pmnato.fortus.exception.validation.EmptyFieldException;
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
        return requestIsPresent() && nameIsValid();
    }

    private boolean nameIsValid() {
        return executePredicate(
                request.name() != null && !request.name().isBlank(),
                EmptyFieldException.class,
                "name"
        );
    }
}
