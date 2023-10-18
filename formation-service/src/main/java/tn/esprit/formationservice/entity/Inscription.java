package tn.esprit.formationservice.entity;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Inscription")
@NoArgsConstructor

public class Inscription  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInscription")
    private Long idInscription; // Clé primaire
    private Long idUser;
    private String nom;
    private String prenom;
    private String email;
    private Date dateInscription;
    private boolean etat;
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(cascade = CascadeType.ALL)
    Formation formation;

}
