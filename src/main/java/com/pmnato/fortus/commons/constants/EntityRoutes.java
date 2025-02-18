package com.pmnato.fortus.commons.constants;

import static com.pmnato.fortus.commons.constants.EntityName.EXERCISE;
import static com.pmnato.fortus.commons.constants.EntityName.USER;
import static com.pmnato.fortus.commons.constants.EntityName.EQUIPMENT;

public final class EntityRoutes {
    public static final String SLASH = "/";

    public static final String USER_ROUTE = SLASH + USER;
    public static final String EXERCISE_ROUTE = SLASH + EXERCISE;
    public static final String EQUIPMENT_ROUTE = SLASH + EQUIPMENT;

    private EntityRoutes() {}
}
