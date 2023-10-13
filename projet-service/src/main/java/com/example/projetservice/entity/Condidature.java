package com.example.projetservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Condidature")
@NoArgsConstructor
public class Condidature {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idCondidature")
    private Long idCondidature; // Clé primaire
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String cv;
    private String lettreMotivation;
    @ManyToOne
    @JoinColumn(name = "ProjetId")
    @JsonIgnore

    private Projet projet;
}
