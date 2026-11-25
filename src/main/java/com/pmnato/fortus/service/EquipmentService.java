package com.pmnato.fortus.service;

import com.pmnato.fortus.commons._interface.IService;
import com.pmnato.fortus.dto.EquipmentDto;
import com.pmnato.fortus.entity.Equipment;
import com.pmnato.fortus.exception.not_found.EquipmentNotFoundException;
import com.pmnato.fortus.exception.validation.ValidationException;
import com.pmnato.fortus.repository.EquipmentRepository;
import com.pmnato.fortus.service.request.EquipmentRequest;
import com.pmnato.fortus.service.validator.EquipmentValidator;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EquipmentService implements IService<EquipmentDto, EquipmentRequest> {
        private final EquipmentRepository repository;

        public List<EquipmentDto> findAll() {
            return repository.findAll().stream()
                    .map(this::mapToDto)
                    .toList();
        }

        public EquipmentDto findById(@NonNull Long id) {
            return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(EquipmentNotFoundException::new);
        }

        public Long save(EquipmentRequest request) {
            EquipmentValidator validator = new EquipmentValidator(request);
            if (!validator.isValidCreation()) {
                throw new ValidationException(validator.getErrors());
            }

            Equipment equipment = new Equipment(
                    null,
                    request.name(),
                    request.weight(),
                    request.imageUrl()
            );
            
            Long id = repository.save(equipment).getId();
            return id;
        }

        public EquipmentDto update(@NonNull Long id, EquipmentRequest request){
            EquipmentValidator validator = new EquipmentValidator(request);
            if (!validator.isValid()) {
                throw new ValidationException(validator.getErrors());
            }

            Equipment equipment = repository.findById(id).orElseThrow(EquipmentNotFoundException::new);
            setData(equipment, request);
            Objects.requireNonNull(equipment);
            repository.save(equipment);
            
            return mapToDto(equipment);
        }

        public void delete(@NonNull Long id) {
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
            equipment.setName(request.name());
            equipment.setWeight(request.weight());
            equipment.setImageUrl(request.imageUrl());
        }
    }
    