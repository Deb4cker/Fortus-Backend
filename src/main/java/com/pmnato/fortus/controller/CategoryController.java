package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.CategoryDto;
import com.pmnato.fortus.service.CategoryService;
import com.pmnato.fortus.service.request.CategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.CATEGORY_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(CATEGORY_ROUTE)
public class CategoryController extends BaseController<CategoryDto, CategoryRequest>
{
    public CategoryController(CategoryService service) {
        super(service);
    }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<CategoryDto>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody CategoryRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<CategoryDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody CategoryRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}