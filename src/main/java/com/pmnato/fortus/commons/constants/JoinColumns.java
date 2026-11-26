package com.pmnato.fortus.commons.constants;

public final class JoinColumns
{
    public static final String USER_ID = "user_id";
    public static final String TRAINING_ID = "training_id";
    public static final String EXERCISE_ID = "exercise_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String EQUIPMENT_ID = "equipment_id";

    //many to many
    public static final String TRAINING_EXERCISE = "training_exercise";
    public static final String EXERCISE_EQUIPMENT = "exercise_equipment";

    private JoinColumns() {}
}
