package com.pmnato.fortus.service;

import com.pmnato.fortus._enum.Difficulty;
import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.entity.Equipment;
import com.pmnato.fortus.entity.Exercise;
import com.pmnato.fortus.exception.not_found.EquipmentNotFoundException;
import com.pmnato.fortus.repository.CategoryRepository;
import com.pmnato.fortus.repository.EquipmentRepository;
import com.pmnato.fortus.repository.ExerciseRepository;
import com.pmnato.fortus.service.request.ExerciseRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExerciseServiceTest {

    @Test
    void createsExerciseAndAssociatesRequestedEquipment() {
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        EquipmentRepository equipmentRepository = mock(EquipmentRepository.class);
        ExerciseService service = new ExerciseService(
                exerciseRepository, categoryRepository, equipmentRepository
        );
        Category category = new Category();
        Equipment equipment = new Equipment(12L, "Barbell", 20, "", new ArrayList<>());

        when(categoryRepository.findById(3L)).thenReturn(Optional.of(category));
        Iterable<Long> equipmentIds = List.of(12L);
        when(equipmentRepository.findAllById(equipmentIds)).thenReturn(List.of(equipment));
        when(exerciseRepository.save(any(Exercise.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ExerciseRequest request = new ExerciseRequest(
                "Deadlift", 5, Difficulty.HARD, "60 seconds", "", "", 3L, List.of(12L)
        );

        service.save(request);

        ArgumentCaptor<Exercise> exerciseCaptor = ArgumentCaptor.forClass(Exercise.class);
        verify(exerciseRepository).save(exerciseCaptor.capture());
        Exercise savedExercise = exerciseCaptor.getValue();
        assertEquals(1, savedExercise.getExerciseEquipments().size());
        assertSame(savedExercise, savedExercise.getExerciseEquipments().get(0).getExercise());
        assertSame(equipment, savedExercise.getExerciseEquipments().get(0).getEquipment());
    }

    @Test
    void rejectsUnknownEquipmentBeforeSavingExercise() {
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        EquipmentRepository equipmentRepository = mock(EquipmentRepository.class);
        ExerciseService service = new ExerciseService(
                exerciseRepository, categoryRepository, equipmentRepository
        );

        when(categoryRepository.findById(3L)).thenReturn(Optional.of(new Category()));
        when(equipmentRepository.findAllById(List.of(12L))).thenReturn(List.of());

        ExerciseRequest request = new ExerciseRequest(
                "Deadlift", 5, Difficulty.HARD, "60 seconds", "", "", 3L, List.of(12L)
        );

        assertThrows(EquipmentNotFoundException.class, () -> service.save(request));
        verify(exerciseRepository, never()).save(any(Exercise.class));
    }
}
