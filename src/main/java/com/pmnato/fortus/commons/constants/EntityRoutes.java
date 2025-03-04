package com.pmnato.fortus.commons.constants;

import static com.pmnato.fortus.commons.constants.APIVersion.API_VERSION;
import static com.pmnato.fortus.commons.constants.EntityName.*;

public final class EntityRoutes
{
    public static final String BASE_ROUTE = "/api/" + API_VERSION + "/";

    public static final String USER_ROUTE = BASE_ROUTE + USER;
    public static final String EXERCISE_ROUTE = BASE_ROUTE + EXERCISE;
    public static final String EQUIPMENT_ROUTE = BASE_ROUTE + EQUIPMENT;
    public static final String TRAINING_ROUTE = BASE_ROUTE + TRAINING;
    public static final String CATEGORY_ROUTE = BASE_ROUTE + CATEGORY;

    public static final String HOME_ROUTE = BASE_ROUTE + "home";

    private EntityRoutes() {}
}
