package com.pmnato.fortus.controller;

import com.pmnato.fortus.dto.Dto;
import com.pmnato.fortus.service.request.Request;
import com.pmnato.fortus.service.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseController<T extends Dto, R extends Request> {

    public abstract ResponseEntity<List<T>> getAll();

    public abstract ResponseEntity<T> getById(@PathVariable long id);

    public abstract ResponseEntity<Long> create(@RequestBody R request);

    public abstract ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody UserRequest request);

    public abstract ResponseEntity<Void> delete (@PathVariable Long id);

    @GetMapping("/")
    public String home(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Nada pequenino, naada 🏊‍♂️</title>
                </head>
                <style>
                    button {
                        color: #090909;
                        padding: 0.7em 1.7em;
                        font-size: 18px;
                        border-radius: 0.5em;
                        background: #e8e8e8;
                        cursor: pointer;
                        border: 1px solid #e8e8e8;
                        transition: all 0.3s;
                        box-shadow: 6px 6px 12px #c5c5c5, -6px -6px 12px #ffffff;
                    }
                           \s
                    button:active {
                        color: #666;
                        box-shadow: inset 4px 4px 12px #c5c5c5, inset -4px -4px 12px #ffffff;
                    }
                           \s
                </style>
                <body>
                    <h1>Ta funcionando!!</h1>
                           \s
                    <a href="https://youtu.be/z5d80AZ-KQ4?si=JZ5o4xgrWGYjHuK-" target="_blank" style="text-decoration: none;">
                        <button>Caminhão do diabo 🚚👺</button>
                    </a>
                </body>
                </html>
               \s""";
    }
}
