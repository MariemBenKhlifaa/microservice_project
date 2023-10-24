package com.example.projetservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)

public class Condidature {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idCondidature")
    private Long idCondidature; // Clé primaire
    private String nom;
    private String prenom;
    private String email;
    private String tel;

    private String pieceJointe;

    private String lettreMotivation;

    @ManyToOne
    @JoinColumn(name = "ProjetId")
    @JsonIgnore

    private Projet projet;



}
