package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.ExerciseDto;
import com.pmnato.fortus.service.ExerciseService;
import com.pmnato.fortus.service.request.ExerciseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.EXERCISE_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(EXERCISE_ROUTE)
public class ExerciseController extends BaseController<ExerciseDto, ExerciseRequest>
{
    public ExerciseController(ExerciseService service) { super(service); }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<ExerciseDto>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<ExerciseDto> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody ExerciseRequest request) {
        return super.create(request);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<ExerciseDto> edit(@PathVariable Long id, @RequestBody ExerciseRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
