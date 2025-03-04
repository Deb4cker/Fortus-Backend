package com.pmnato.fortus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityName.CATEGORIES;
import static com.pmnato.fortus.commons.constants.EntityName.CATEGORY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = CATEGORIES)
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageUrl;

    @OneToMany(mappedBy = CATEGORY, cascade = CascadeType.ALL)
    private List<Exercise> exercises;

}