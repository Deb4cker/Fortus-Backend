package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.service.TrainingService;
import com.pmnato.fortus.service.request.ExerciseRequest;
import com.pmnato.fortus.service.request.TrainingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.TRAINING_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(TRAINING_ROUTE)
public class TrainingController extends BaseController<TrainingDto, TrainingRequest>
{
    public TrainingController(TrainingService service) {super(service);}

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<TrainingDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<TrainingDto> getById(long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(TrainingRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(Long id, TrainingRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
