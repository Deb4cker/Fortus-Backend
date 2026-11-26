package com.pmnato.fortus.service.validator;

import com.pmnato.fortus._enum.DayOfWeek;
import com.pmnato.fortus._enum.Difficulty;
import com.pmnato.fortus.repository.UserRepository;
import com.pmnato.fortus.service.request.CategoryRequest;
import com.pmnato.fortus.service.request.EquipmentRequest;
import com.pmnato.fortus.service.request.ExerciseRequest;
import com.pmnato.fortus.service.request.TrainingRequest;
import com.pmnato.fortus.service.request.UserRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityValidatorTest {

    @Test
    void categoryValidatorRequiresANameAndAllowsAnEmptyImageUrl() {
        CategoryValidator validValidator = new CategoryValidator(new CategoryRequest("Strength", ""));
        assertTrue(validValidator.isValid());

        CategoryValidator invalidValidator = new CategoryValidator(new CategoryRequest(" ", ""));
        assertFalse(invalidValidator.isValid());
        assertEquals(List.of("The field name is empty."), invalidValidator.getErrors());
    }

    @Test
    void equipmentValidatorRequiresANameAndNonNegativeFiniteWeight() {
        EquipmentValidator validValidator = new EquipmentValidator(new EquipmentRequest("Dumbbell", 5, ""));
        assertTrue(validValidator.isValid());

        EquipmentValidator invalidValidator = new EquipmentValidator(new EquipmentRequest("", -1, ""));
        assertFalse(invalidValidator.isValid());
        assertEquals(List.of(
                "The field name is empty.",
                "Weight must be a finite, non-negative number."
        ), invalidValidator.getErrors());

        EquipmentValidator notANumberValidator = new EquipmentValidator(
                new EquipmentRequest("Dumbbell", Double.NaN, "")
        );
        assertFalse(notANumberValidator.isValid());
        assertEquals(1, notANumberValidator.getErrors().size());
    }

    @Test
    void exerciseValidatorRequiresNameDifficultyRepetitionsAndCategory() {
        ExerciseRequest validRequest = new ExerciseRequest(
                "Push-up", 12, Difficulty.MEDIUM, "30 seconds", "", "", 1L, List.of(1L, 2L)
        );
        assertTrue(new ExerciseValidator(validRequest).isValid());
        assertTrue(new ExerciseValidator(
                new ExerciseRequest("Push-up", 12, Difficulty.MEDIUM, "30 seconds", "", "", 1L, null)
        ).isValid());

        ExerciseValidator invalidValidator = new ExerciseValidator(
                new ExerciseRequest("Push-up", 0, Difficulty.MEDIUM, "30 seconds", "", "", 1L, null)
        );
        assertFalse(invalidValidator.isValid());
        assertEquals(List.of("Repetitions must be greater than zero."), invalidValidator.getErrors());

        ExerciseValidator multipleErrorsValidator = new ExerciseValidator(
                new ExerciseRequest("", 0, null, "30 seconds", "", "", null, null)
        );
        assertFalse(multipleErrorsValidator.isValid());
        assertEquals(4, multipleErrorsValidator.getErrors().size());

        ExerciseValidator invalidEquipmentIdsValidator = new ExerciseValidator(
                new ExerciseRequest("Push-up", 12, Difficulty.MEDIUM, "30 seconds", "", "", 1L, List.of(0L))
        );
        assertFalse(invalidEquipmentIdsValidator.isValid());
        assertEquals(List.of("Each equipmentId must be a positive number."),
                invalidEquipmentIdsValidator.getErrors());
    }

    @Test
    void trainingValidatorRequiresDescriptionDateAndDayAndNonNegativeDuration() {
        TrainingRequest validRequest = new TrainingRequest(
                "Strength session", LocalDate.of(2026, 11, 25), false, 3_600_000, DayOfWeek.WEDNESDAY
        );
        assertTrue(new TrainingValidator(validRequest).isValid());

        TrainingValidator invalidValidator = new TrainingValidator(
                new TrainingRequest("", LocalDate.of(2026, 11, 25), false, 0, DayOfWeek.WEDNESDAY)
        );
        assertFalse(invalidValidator.isValid());
        assertEquals(List.of("The field description is empty."), invalidValidator.getErrors());

        TrainingValidator multipleErrorsValidator = new TrainingValidator(
                new TrainingRequest("", null, false, -1, null)
        );
        assertFalse(multipleErrorsValidator.isValid());
        assertEquals(4, multipleErrorsValidator.getErrors().size());
    }

    @Test
    void userValidatorCollectsAllIndependentErrors() {
        UserValidator validator = new UserValidator(
                new UserRequest("", null, "", "invalid-email", "123", "different"),
                mock(UserRepository.class)
        );

        assertFalse(validator.isValidCreation());
        assertEquals(4, validator.getErrors().size());
    }
}
