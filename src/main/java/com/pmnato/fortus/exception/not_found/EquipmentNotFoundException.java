package com.pmnato.fortus.exception.not_found;

import static com.pmnato.fortus.commons.constants.EntityName.CAPITALIZED_EQUIPMENT;

public class EquipmentNotFoundException extends NotFoundException  {
    public EquipmentNotFoundException(){
        super(CAPITALIZED_EQUIPMENT);
    }
}
