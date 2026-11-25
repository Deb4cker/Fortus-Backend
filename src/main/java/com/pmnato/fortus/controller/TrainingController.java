package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.TrainingDto;
import com.pmnato.fortus.service.TrainingService;
import com.pmnato.fortus.service.request.TrainingRequest;
import com.pmnato.fortus.service.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<TrainingDto>>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<ApiResponse<TrainingDto>> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<ApiResponse<Long>> create(@RequestBody TrainingRequest request) {
        return super.create(request);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<ApiResponse<TrainingDto>> edit(@PathVariable Long id, @RequestBody TrainingRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
