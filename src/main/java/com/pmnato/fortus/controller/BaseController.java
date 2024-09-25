package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.Dto;
import com.pmnato.fortus.service.request.Request;
import com.pmnato.fortus.service.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends Dto, R extends Request> {

    public abstract ResponseEntity<List<T>> getAll();

    public abstract ResponseEntity<T> getById(@PathVariable long id);

    public abstract ResponseEntity<Long> create(@RequestBody R request);

    public abstract ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody UserRequest request);

    public abstract ResponseEntity<Void> delete (@PathVariable Long id);
}
