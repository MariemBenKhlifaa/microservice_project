package tn.esprit.formationservice.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table( name = "Formation")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFormation")
public class Formation  implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "formation")

    List<Inscription> inscriptions;



}
