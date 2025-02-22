package com.pmnato.fortus.exception.not_found;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_USER;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException() {
        super(CAPITALIZED_USER);
    }
}
