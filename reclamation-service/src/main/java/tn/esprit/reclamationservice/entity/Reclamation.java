package tn.esprit.reclamationservice.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor

@Table( name = "Reclamation")
public class Reclamation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idReclamation")
    private Long idReclamation;
    private String sujet;
    private String description;
    private Date dateSoumission;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    @Enumerated(EnumType.STRING)
    private Evaluation evaluation;
    private boolean etat = false;
    private String pieceJointe;

    @OneToOne(mappedBy = "reclamation")
    private Reponse reponse;

    @PrePersist
    protected void onCreate() {
        dateSoumission = new Date(); // Set the dateSoumission to the current date
    }
}