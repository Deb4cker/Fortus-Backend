package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.CategoryDto;
import com.pmnato.fortus.service.CategoryService;
import com.pmnato.fortus.service.request.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityRoutes.CATEGORY_ROUTE;
import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@RestController
@RequestMapping(CATEGORY_ROUTE)
public class CategoryController extends BaseController<CategoryDto, CategoryRequest>
{
    public CategoryController(CategoryService service) { super(service); }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<List<CategoryDto>> getAll(){
        return super.getAll();
    }

    @Override
    @PostMapping(CREATE)
    public ResponseEntity<Long> create(@RequestBody CategoryRequest request) {
       return super.create(request);
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<CategoryDto> getById(@PathVariable long id) {
        return super.getById(id);
    }
    
    @Override
    @PutMapping(EDIT)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return super.edit(id, request);
    }

    @Override
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}