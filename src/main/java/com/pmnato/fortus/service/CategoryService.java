package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.CategoryDto;
import com.pmnato.fortus.entity.Category;
import com.pmnato.fortus.exception.not_found.CategoryNotFoundException;
import com.pmnato.fortus.repository.CategoryRepository;
import com.pmnato.fortus.service.request.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@AllArgsConstructor
public class CategoryService implements IService<CategoryDto, CategoryRequest>
{
    private final CategoryRepository repository;

    public List<CategoryDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public CategoryDto findById(Long id) {
        Category Category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return mapToDto(Category);
    }

    public Long save(CategoryRequest request) {
        Category Category = new Category(
                null,
                request.name(),
                request.imageUrl(),
                new ArrayList<>()
        );

        Long id = repository.save(Category).getId();
        return id;
    }

    public void update(Long id, CategoryRequest request) {
        Category Category = repository.getReferenceById(id);
        setData(Category, request);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException();
        }
    }

    private CategoryDto mapToDto(Category Category) {
        return new CategoryDto(
                Category.getId(),
                Category.getName(),
                Category.getImageUrl()
        );
    }

    private void setData(Category Category, CategoryRequest request) {
        Category.setName(request.name());
        Category.setImageUrl(request.imageUrl());
    }
}
