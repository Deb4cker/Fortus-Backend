package com.pmnato.fortus.controller;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.Dto;
import com.pmnato.fortus.service.request.Request;
import com.pmnato.fortus.service.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
public abstract class BaseController<TDto extends Dto, TRequest extends Request>
{
    protected final IService<TDto, TRequest> service;

    public ResponseEntity<ApiResponse<List<TDto>>> getAll(){
        return ResponseEntity.ok(ApiResponse.collection(service.findAll()));
    }

    public ResponseEntity<ApiResponse<TDto>> getById(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.findById(id)));
    }

    public ResponseEntity<ApiResponse<Long>> create(@RequestBody TRequest request) {
        Long id = service.save(request);
        return ResponseEntity.ok(new ApiResponse<>(id));
    }

    public ResponseEntity<ApiResponse<TDto>> edit(@PathVariable Long id, @RequestBody TRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(service.update(id, request)));
    }

    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>((Void) null));
    }
}
