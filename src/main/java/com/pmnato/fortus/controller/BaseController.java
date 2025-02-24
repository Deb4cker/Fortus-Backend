package com.pmnato.fortus.controller;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.Dto;
import com.pmnato.fortus.service.request.Request;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pmnato.fortus.commons.constants.RestRoutes.*;

@AllArgsConstructor
public abstract class BaseController<TDto extends Dto, TRequest extends Request>
{
    protected final IService<TDto, TRequest> service;

    public ResponseEntity<List<TDto>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<TDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<Long> create(@RequestBody TRequest request) {
        Long id = service.save(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody TRequest request) {
        service.update(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
