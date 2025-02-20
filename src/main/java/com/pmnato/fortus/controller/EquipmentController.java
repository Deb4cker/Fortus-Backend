package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.EquipmentDto;
import com.pmnato.fortus.service.EquipmentService;
import com.pmnato.fortus.service.request.EquipmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.EQUIPMENT_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@AllArgsConstructor
@RequestMapping(EQUIPMENT_ROUTE)
public class EquipmentController extends BaseController<EquipmentDto, EquipmentRequest> {

    private final EquipmentService equipmentService;

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<EquipmentDto>> getAll(){
        return new ResponseEntity<>(equipmentService.findAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody EquipmentRequest request) {
        Long id = equipmentService.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<EquipmentDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(equipmentService.findById(id), HttpStatus.OK);
    }
    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        equipmentService.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
