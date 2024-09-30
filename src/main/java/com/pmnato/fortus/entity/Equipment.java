package com.pmnato.fortus.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Equipment {
    private long id;
    private String name;
    private double weight;
    private String imageUrl;

}
