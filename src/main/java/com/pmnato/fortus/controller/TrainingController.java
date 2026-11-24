package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.service.TrainingService;
import com.pmnato.fortus.service.request.TrainingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.TRAINING_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(TRAINING_ROUTE)
public class TrainingController extends BaseController<TrainingDto, TrainingRequest>
{
    public TrainingController(TrainingService service) { super(service); }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<TrainingDto>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<TrainingDto> getById(long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(TrainingRequest request) {
        return super.create(request);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(Long id, TrainingRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }
}
