package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.service.request.TrainingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingController extends BaseController<TrainingDto, TrainingRequest> {

    @Override
    public ResponseEntity<List<TrainingDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrainingDto> getById(long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> create(TrainingRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> edit(Long id, TrainingRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);    }
}
