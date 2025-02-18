package com.pmnato.fortus.repository;

import com.pmnato.fortus.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}