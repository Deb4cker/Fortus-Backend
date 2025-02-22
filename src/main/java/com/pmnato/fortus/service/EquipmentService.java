package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.EquipmentDto;
import com.pmnato.fortus.entity.Equipment;
import com.pmnato.fortus.exception.not_found.EquipmentNotFoundException;
import com.pmnato.fortus.repository.EquipmentRepository;
import com.pmnato.fortus.service.request.EquipmentRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EquipmentService implements IService<EquipmentDto, EquipmentRequest> {
        private final EquipmentRepository repository;

        public List<EquipmentDto> findAll() {
            return repository.findAll().stream()
                    .map(this::mapToDto)
                    .toList();
        }

        public EquipmentDto findById(Long id) {
            Equipment equipment = repository.findById(id).orElseThrow(EquipmentNotFoundException::new);
            return mapToDto(equipment);
        }
        public Long save(EquipmentRequest request) {
            Equipment equipment = new Equipment(
                    request.id(),
                    request.name(),
                    request.weight(),
                    request.imageUrl()
            );
            Long id = repository.save(equipment).getId();
            return id;
        }
        public void update(Long id, EquipmentRequest request){
            Equipment equipment = repository.getReferenceById(id);
            setData(equipment, request);
        }
        public void delete(Long id) {
            try {
                repository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new EquipmentNotFoundException();
            }
        }
        private EquipmentDto mapToDto(Equipment equipment) {
            return new EquipmentDto(
                    equipment.getId(),
                    equipment.getName(),
                    equipment.getWeight(),
                    equipment.getImageUrl()
            );
        }
        private void setData (Equipment equipment, EquipmentRequest request){
            equipment.setId(request.id());
            equipment.setName(request.name());
            equipment.setWeight(request.weight());
            equipment.setImageUrl(request.imageUrl());
        }
    }