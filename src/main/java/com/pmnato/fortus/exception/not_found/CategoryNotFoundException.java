package com.pmnato.fortus.exception.not_found;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_CATEGORY;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super(CAPITALIZED_CATEGORY);
    }
}
