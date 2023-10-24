package tn.esprit.reclamationservice.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String nom;
    private String sujet;
    private String description;
    private Date dateSoumission;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    @Enumerated(EnumType.STRING)
    private Evaluation evaluation;
    private boolean etat = false;
    private String pieceJointe;

    @OneToOne(mappedBy = "reclamation", cascade = CascadeType.REMOVE)
    private Reponse reponse;

    @JsonBackReference
    public Reponse getReponse() {
        return reponse;
    }

    @PrePersist
    protected void onCreate() {
        dateSoumission = new Date(); // Set the dateSoumission to the current date
    }
}