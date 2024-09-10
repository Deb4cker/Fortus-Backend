package com.pmnato.fortus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static com.pmnato.fortus.commons.constants.JoinColumns.USER_ID;

@Entity
public class Training
{
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = USER_ID, nullable = false)
    @JsonBackReference
    private User user;
}
