package com.pmnato.fortus.commons._interface;

import com.pmnato.fortus.dto.Dto;
import com.pmnato.fortus.service.request.Request;

import java.util.List;

public interface IService<TDto extends Dto, TRequest extends Request>
{
    Long save(TRequest request);
    List<TDto> findAll();
    TDto findById(Long id);
    void update(Long id, TRequest request);
    void delete(Long id);
}
