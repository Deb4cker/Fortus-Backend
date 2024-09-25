package com.pmnato.fortus.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pmnato.fortus._enum.DayOfWeek;
import static com.pmnato.fortus.commons.constants.JoinColumns.USER_ID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String description;
    private LocalDate date;
    private boolean status;
    private long durationTimeMillis;
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = USER_ID, nullable = false)
    @JsonBackReference
    private User user;
}
