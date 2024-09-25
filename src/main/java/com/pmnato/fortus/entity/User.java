package com.pmnato.fortus.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import static com.pmnato.fortus.commons.constants.EntityName.USER;
import static com.pmnato.fortus.commons.constants.EntityName.USERS;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = USERS)
public class User
{
    @Id //para o JPA identificar que esse atributo é o id
    @GeneratedValue(strategy = GenerationType.AUTO)  //valor gerado: estratégia automática
    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private String email;
    private String password;
    private String salt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy=USER)
    @JsonManagedReference
    private List<Training> trainings;
}
