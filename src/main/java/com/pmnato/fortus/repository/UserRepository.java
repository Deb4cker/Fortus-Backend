package com.pmnato.fortus.repository;

import com.pmnato.fortus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
