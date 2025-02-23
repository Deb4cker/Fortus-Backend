package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.service.ExerciseService;
import com.pmnato.fortus.service.request.ExerciseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.EXERCISE_ROUTE;

@RestController
@RequestMapping(EXERCISE_ROUTE)
public class ExerciseController extends BaseController<ExerciseDto, ExerciseRequest>
{
    public ExerciseController(ExerciseService service) {super(service);}

    @Override
    public ResponseEntity<List<ExerciseDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExerciseDto> getById(long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> create(ExerciseRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> edit(Long id, ExerciseRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
