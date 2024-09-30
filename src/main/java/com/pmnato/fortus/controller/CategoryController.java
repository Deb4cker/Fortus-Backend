package com.pmnato.fortus.controller;

import com.pmnato.fortus.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @GetMapping("/category/all")
    public Category[] getAll() {
        return new Category[]{
                new Category(1L, "Superior", "ImageUrl"),
                new Category(2L, "Inferior", "ImageUrl"),
                new Category(3L, "Abdomen", "ImageUrl"),
                new Category(4L, "Torax", "ImageUrl"),

        };
    }
}