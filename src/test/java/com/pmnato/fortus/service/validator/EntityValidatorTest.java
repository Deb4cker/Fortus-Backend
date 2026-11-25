package com.pmnato.fortus.service.validator;

import com.pmnato.fortus._enum.DayOfWeek;
import com.pmnato.fortus._enum.Difficulty;
import com.pmnato.fortus.exception.validation.EmptyFieldException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.service.request.CategoryRequest;
import com.pmnato.fortus.service.request.EquipmentRequest;
import com.pmnato.fortus.service.request.ExerciseRequest;
import com.pmnato.fortus.service.request.TrainingRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityValidatorTest {

    @Test
    void categoryValidatorRequiresANameAndAllowsAnEmptyImageUrl() {
        assertTrue(new CategoryValidator(new CategoryRequest("Strength", "")).isValid());
        assertThrows(EmptyFieldException.class,
                () -> new CategoryValidator(new CategoryRequest(" ", "")).isValid());
    }

    @Test
    void equipmentValidatorRequiresANameAndNonNegativeFiniteWeight() {
        assertTrue(new EquipmentValidator(new EquipmentRequest("Dumbbell", 5, "")).isValid());
        assertThrows(EmptyFieldException.class,
                () -> new EquipmentValidator(new EquipmentRequest("", 5, "")).isValid());
        assertThrows(ValidationException.class,
                () -> new EquipmentValidator(new EquipmentRequest("Dumbbell", -1, "")).isValid());
        assertThrows(ValidationException.class,
                () -> new EquipmentValidator(new EquipmentRequest("Dumbbell", Double.NaN, "")).isValid());
    }

    @Test
    void exerciseValidatorRequiresNameDifficultyRepetitionsAndCategory() {
        ExerciseRequest validRequest = new ExerciseRequest(
                "Push-up", 12, Difficulty.MEDIUM, "30 seconds", "", "", 1L
        );
        assertTrue(new ExerciseValidator(validRequest).isValid());

        assertThrows(ValidationException.class, () -> new ExerciseValidator(
                new ExerciseRequest("Push-up", 0, Difficulty.MEDIUM, "30 seconds", "", "", 1L)
        ).isValid());
        assertThrows(ValidationException.class, () -> new ExerciseValidator(
                new ExerciseRequest("Push-up", 12, Difficulty.MEDIUM, "30 seconds", "", "", null)
        ).isValid());
        assertThrows(EmptyFieldException.class, () -> new ExerciseValidator(
                new ExerciseRequest("Push-up", 12, null, "30 seconds", "", "", 1L)
        ).isValid());
    }

    @Test
    void trainingValidatorRequiresDescriptionDateAndDayAndNonNegativeDuration() {
        TrainingRequest validRequest = new TrainingRequest(
                "Strength session", LocalDate.of(2026, 11, 25), false, 3_600_000, DayOfWeek.WEDNESDAY
        );
        assertTrue(new TrainingValidator(validRequest).isValid());

        assertThrows(EmptyFieldException.class, () -> new TrainingValidator(
                new TrainingRequest("", LocalDate.of(2026, 11, 25), false, 0, DayOfWeek.WEDNESDAY)
        ).isValid());
        assertThrows(ValidationException.class, () -> new TrainingValidator(
                new TrainingRequest("Strength session", LocalDate.of(2026, 11, 25), false, -1, DayOfWeek.WEDNESDAY)
        ).isValid());
    }
}
