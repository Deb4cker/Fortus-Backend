package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.entity.Exercise;
import com.pmnato.fortus.repository.CategoryRepository;
import com.pmnato.fortus.repository.ExerciseRepository;
import com.pmnato.fortus.service.request.ExerciseRequest;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.pmnato.fortus.exception.not_found.ExerciseNotFoundException;

import java.util.ArrayList;
import java.util.List;

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

    public ExerciseDto findById(Long id) {
        Exercise exercise = repository.findById(id).orElseThrow(ExerciseNotFoundException::new);
        return mapToDto(exercise);
    }

    public Long save(ExerciseRequest request) {
        Category category = categoryRepository.findById(request.categoryId()).get();

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

    public void update(Long id, ExerciseRequest request){
        Exercise exercise = repository.getReferenceById(id);
        setData(exercise, request);
    }

    public void delete(Long id) {
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
