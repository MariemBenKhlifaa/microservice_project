package tn.esprit.formationservice.entity;
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
@Table( name = "Formation")
@NoArgsConstructor
public class Formation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idFormation")
    private Long idFormation; // Clé primaire
    private String titre;
    private String description;

    private Date date_debut;
    private Date date_fin;
    private String image;
    private String prix;



}
