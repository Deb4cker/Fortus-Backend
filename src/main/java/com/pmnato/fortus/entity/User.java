package com.pmnato.fortus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pmnato.fortus.utils.Encryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pmnato.fortus.commons.constants.EntityName.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = USERS)
public class User
{
    @Id //para o JPA identificar que esse atributo é o id
    @GeneratedValue(strategy = GenerationType.AUTO)  //valor gerado: estratégia automática
    private Long id;
    private String name;
    private String email;
    private String password;
    private String salt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy=USER)
    @JsonManagedReference
    private List<Training> trainings;

    //ESSE CONSTRUTOR É TEMPORÁRIO, SE ESTIVEREM OLHANDO ESSA CLASSE COMO REFERENCIA, NÃO COPIEM ESSE CARA
    public User(Long id, String email,  String name, String password, String salt) {
        this.id = id;
        this.email = email;
        this.name = name;

        var encryptData = Encryptor.getEncryptData(password);

        this.password = encryptData.hash();
        this.salt = encryptData.salt();
    }
}
