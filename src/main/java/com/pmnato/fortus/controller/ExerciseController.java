package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.service.ExerciseService;
import com.pmnato.fortus.service.request.ExerciseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.EXERCISE_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(EXERCISE_ROUTE)
public class ExerciseController extends BaseController<ExerciseDto, ExerciseRequest>
{
    public ExerciseController(ExerciseService service) {super(service);}

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<ExerciseDto>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<ExerciseDto> getById(long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(ExerciseRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(Long id, ExerciseRequest request) {
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
