package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.entity.Exercise;
import com.pmnato.fortus.entity.Equipment;
import com.pmnato.fortus.entity.ExerciseEquipment;
import com.pmnato.fortus.exception.not_found.EquipmentNotFoundException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.repository.CategoryRepository;
import com.pmnato.fortus.repository.EquipmentRepository;
import com.pmnato.fortus.repository.ExerciseRepository;
import com.pmnato.fortus.service.request.ExerciseRequest;
import com.pmnato.fortus.service.validator.ExerciseValidator;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmnato.fortus.exception.not_found.CategoryNotFoundException;
import com.pmnato.fortus.exception.not_found.ExerciseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService implements IService<ExerciseDto, ExerciseRequest> {
    private final ExerciseRepository repository;
    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;

    public List<ExerciseDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public ExerciseDto findById(@NonNull Long id) {
        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        return mapToDto(exercise);
    }

    @Transactional
    public Long save(ExerciseRequest request) {
        ExerciseValidator validator = new ExerciseValidator(request);
        if (!validator.isValidCreation()) {
            throw new ValidationException(validator.getErrors());
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        List<Equipment> equipments = findEquipments(request.equipmentIds());

        Exercise exercise = new Exercise(
                null,
                request.name(),
                request.difficultyLevel(),
                request.repetitions(),
                request.suggestedTime(),
                request.imageUrl(),
                request.VideoUrl(),
                new ArrayList<>(),
                new ArrayList<>(),
                category
        );
        addEquipmentAssociations(exercise, equipments);

        return repository.save(exercise).getId();
    }

    @Transactional
    public ExerciseDto update(@NonNull Long id, ExerciseRequest request){
        ExerciseValidator validator = new ExerciseValidator(request);
        if (!validator.isValid()) {
            throw new ValidationException(validator.getErrors());
        }

        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        setData(exercise, request);
        exercise.setCategory(category);
        if (request.equipmentIds() != null) {
            List<Equipment> equipments = findEquipments(request.equipmentIds());
            exercise.getExerciseEquipments().clear();
            addEquipmentAssociations(exercise, equipments);
        }
        repository.save(exercise);
        
        return mapToDto(exercise);
    }

    @Transactional
    public void delete(@NonNull Long id) {
        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        repository.delete(exercise);
    }

    private ExerciseDto mapToDto(Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getRepetitions(),
                exercise.getDifficultyLevel(),
                exercise.getSuggestedTime(),
                exercise.getImageUrl(),
                exercise.getVideoUrl()
        );
    }

    private void setData (Exercise exercise, ExerciseRequest request){
        exercise.setDifficultyLevel(request.difficultyLevel());
        exercise.setName(request.name());
        exercise.setRepetitions(request.repetitions());
        exercise.setImageUrl(request.imageUrl());
        exercise.setVideoUrl(request.VideoUrl());
        exercise.setSuggestedTime(request.suggestedTime());
    }

    private List<Equipment> findEquipments(List<Long> equipmentIds) {
        if (equipmentIds == null || equipmentIds.isEmpty()) {
            return List.of();
        }

        List<Long> distinctIds = equipmentIds.stream().distinct().toList();
        Map<Long, Equipment> equipmentsById = equipmentRepository.findAllById(distinctIds).stream()
                .collect(Collectors.toMap(Equipment::getId, equipment -> equipment, (first, ignored) -> first));

        if (equipmentsById.size() != distinctIds.size()) {
            throw new EquipmentNotFoundException();
        }

        return distinctIds.stream().map(equipmentsById::get).toList();
    }

    private void addEquipmentAssociations(Exercise exercise, List<Equipment> equipments) {
        for (Equipment equipment : equipments) {
            exercise.getExerciseEquipments().add(new ExerciseEquipment(null, exercise, equipment));
        }
    }
}
