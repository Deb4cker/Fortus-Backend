package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.entity.Exercise;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.repository.CategoryRepository;
import com.pmnato.fortus.repository.ExerciseRepository;
import com.pmnato.fortus.service.request.ExerciseRequest;
import com.pmnato.fortus.service.validator.ExerciseValidator;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pmnato.fortus.exception.not_found.CategoryNotFoundException;
import com.pmnato.fortus.exception.not_found.ExerciseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ExerciseService implements IService<ExerciseDto, ExerciseRequest> {
    private final ExerciseRepository repository;
    private final CategoryRepository categoryRepository;

    public List<ExerciseDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public ExerciseDto findById(@NonNull Long id) {
        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        return mapToDto(exercise);
    }

    public Long save(@NonNull ExerciseRequest request) {
        if (!new ExerciseValidator(request).isValidCreation()) {
            throw new ValidationException();
        }

        Long categoryId = Objects.requireNonNull(request.categoryId());
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

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

        Long id = repository.save(exercise).getId();
        return id;
    }

    public ExerciseDto update(@NonNull Long id, ExerciseRequest request){
        if (!new ExerciseValidator(request).isValid()) {
            throw new ValidationException();
        }

        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        setData(exercise, request);
        Objects.requireNonNull(exercise);
        repository.save(exercise);
        
        return mapToDto(exercise);
    }

    public void delete(@NonNull Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ExerciseNotFoundException();
        }
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
}
