package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.EquipmentDto;
import com.pmnato.fortus.service.EquipmentService;
import com.pmnato.fortus.service.request.EquipmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.EQUIPMENT_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(EQUIPMENT_ROUTE)
public class EquipmentController extends BaseController<EquipmentDto, EquipmentRequest>
{
    public EquipmentController(EquipmentService service) { super(service); }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<EquipmentDto>> getAll(){
        return super.getAll();
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody EquipmentRequest request) {
        return super.create(request);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<EquipmentDto> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
