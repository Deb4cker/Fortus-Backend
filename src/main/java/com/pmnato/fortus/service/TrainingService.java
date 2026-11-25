package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.entity.Training;
import com.pmnato.fortus.exception.not_found.TrainingNotFoundException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.repository.TrainingRepository;
import com.pmnato.fortus.service.request.TrainingRequest;
import com.pmnato.fortus.service.validator.TrainingValidator;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TrainingService implements IService<TrainingDto, TrainingRequest> {
    private final TrainingRepository repository;

    public List<TrainingDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }
    
    public TrainingDto findById(@NonNull Long id) {
        Training training = repository.findById(id).orElseThrow(TrainingNotFoundException::new);
        return mapToDto(training);
    }
    
    public Long save(TrainingRequest request) {
        TrainingValidator validator = new TrainingValidator(request);
        if (!validator.isValidCreation()) {
            throw new ValidationException(validator.getErrors());
        }

        Training training = new Training(
                null,
                request.description(),
                request.date(),
                request.status(),
                request.durationTimeMillis(),
                request.dayOfWeek()
        );
        
        Long id = repository.save(training).getId();
        return id;
    }

    public TrainingDto update(@NonNull Long id, TrainingRequest request){
        TrainingValidator validator = new TrainingValidator(request);
        if (!validator.isValid()) {
            throw new ValidationException(validator.getErrors());
        }

        Training training = repository.findById(id).orElseThrow(TrainingNotFoundException::new);
        setData(training, request);
        Objects.requireNonNull(training);
        repository.save(training);
        
        return mapToDto(training);
    }

    public void delete(@NonNull Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TrainingNotFoundException();
        }
    }

    private TrainingDto mapToDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getDescription(),
                training.getDate(),
                training.isStatus(),
                training.getDurationTimeMillis(),
                training.getDayOfWeek()
                );
    }
    
    private void setData (Training training, TrainingRequest request){
        training.setDescription(request.description());
        training.setDate(request.date());
        training.setStatus(request.status());
        training.setDurationTimeMillis(request.durationTimeMillis());
        training.setDayOfWeek(request.dayOfWeek());
    }
}
