package tn.esprit.reclamationservice.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor

@Table( name = "Reponse")
public class Reponse {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idReponse")
    private Long idReponse;
    private String sujet;
    private String description;
    private Date dateReponse;
    private String pieceJointe;

    @OneToOne
    private Reclamation reclamation;
    @JsonManagedReference
    public Reclamation getReclamation() {
        return reclamation;
    }

    @PrePersist
    protected void onCreate() {
        dateReponse = new Date(); // Set the dateSoumission to the current date
    }

}