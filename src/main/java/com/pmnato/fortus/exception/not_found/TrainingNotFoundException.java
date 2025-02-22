package com.pmnato.fortus.exception.not_found;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_TRAINING;

public class TrainingNotFoundException extends NotFoundException {
    public TrainingNotFoundException(){
        super(CAPITALIZED_TRAINING);
    }
}
