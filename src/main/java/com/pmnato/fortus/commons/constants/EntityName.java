package com.pmnato.fortus.commons.constants;

import org.springframework.util.StringUtils;

public final class EntityName {
    public static final String USER      = "user";
    public static final String USER_DATA = "user_data";
    public static final String TRAINING  = "training";
    public static final String EXERCISE  = "exercise";
    public static final String EQUIPMENT = "equipment";
    public static final String CATEGORY  = "category";

    //Capitalized single
    public static final String CAPITALIZED_USER      = StringUtils.capitalize(USER);
    public static final String CAPITALIZED_USER_DATA = StringUtils.capitalize(USER_DATA);
    public static final String CAPITALIZED_TRAINING  = StringUtils.capitalize(TRAINING);
    public static final String CAPITALIZED_EXERCISE  = StringUtils.capitalize(EXERCISE);
    public static final String CAPITALIZED_EQUIPMENT = StringUtils.capitalize(EQUIPMENT);
    public static final String CAPITALIZED_CATEGORY  = StringUtils.capitalize(CATEGORY);

    //plurals
    public static final String USERS      = "users";
    public static final String USER_DATAS = "user_datas";
    public static final String TRAININGS  = "trainings";
    public static final String EXERCISES  = "exercises";
    public static final String EQUIPMENTS = "equipments";
    public static final String CATEGORIES = "categories";

    //Capitalized plurals
    public static final String CAPITALIZED_USERS      = StringUtils.capitalize(USERS);
    public static final String CAPITALIZED_USER_DATAS = StringUtils.capitalize(USER_DATAS);
    public static final String CAPITALIZED_TRAININGS  = StringUtils.capitalize(TRAININGS);
    public static final String CAPITALIZED_EXERCISES  = StringUtils.capitalize(EXERCISES);
    public static final String CAPITALIZED_EQUIPMENTS = StringUtils.capitalize(EQUIPMENTS);
    public static final String CAPITALIZED_CATEGORIES = StringUtils.capitalize(CATEGORIES);

    private EntityName() {}
}
