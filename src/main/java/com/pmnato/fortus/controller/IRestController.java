package com.pmnato.fortus.controller;

import com.pmnato.fortus.service.request.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestController {
    ResponseEntity<Object> get   (@PathVariable Long id);
    ResponseEntity<Object> delete(@PathVariable Long id);
    ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Request request);
    ResponseEntity<Object> create(@RequestBody Request request);
    ResponseEntity<Object> getAll();
}
