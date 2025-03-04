package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.entity.Training;
import com.pmnato.fortus.exception.not_found.TrainingNotFoundException;
import com.pmnato.fortus.repository.TrainingRepository;
import com.pmnato.fortus.service.request.TrainingRequest;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class TrainingService implements IService<TrainingDto, TrainingRequest> {
    private final TrainingRepository repository;

    public List<TrainingDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }
    public TrainingDto findById(Long id) {
        Training training = repository.findById(id).orElseThrow(TrainingNotFoundException::new);
        return mapToDto(training);
    }
    public Long save(TrainingRequest request) {
        Training training = new Training(
                request.id(),
                request.description(),
                request.date(),
                request.status(),
                request.durationTimeMillis(),
                request.dayOfWeek()
        );
        Long id = repository.save(training).getId();
        return id;
    }
    public void update(Long id, TrainingRequest request){
        Training training = repository.getReferenceById(id);
        setData(training, request);
    }
    public void delete(Long id) {
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
        training.setId(request.id());
        training.setDescription(request.description());
        training.setDate(request.date());
        training.setStatus(request.status());
        training.setDurationTimeMillis(request.durationTimeMillis());
        training.setDayOfWeek(request.dayOfWeek());
    }
}
