package tn.esprit.reclamationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.reclamationservice.entity.Categorie;
import tn.esprit.reclamationservice.entity.Evaluation;
import tn.esprit.reclamationservice.entity.Reclamation;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    List<Reclamation> findByEtatFalse();
    List<Reclamation> findByEtatTrue();
    List<Reclamation> findByCategorieAndEtatFalse(Categorie categorie);
    List<Reclamation> findByEvaluationAndEtatFalse(Evaluation evaluation);
    List<Reclamation> findByEvaluationAndCategorieAndEtatFalse(Evaluation evaluation,Categorie categorie);

}