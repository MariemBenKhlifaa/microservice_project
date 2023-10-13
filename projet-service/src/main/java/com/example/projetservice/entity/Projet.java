package com.example.projetservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Projet")
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ProjetId")
    private Long ProjetId; // Clé primaire
    private String nom;
    private String description;

    private Date dateProjet;
    private Float prix;
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Condidature> condidatures = new ArrayList<>();
}
