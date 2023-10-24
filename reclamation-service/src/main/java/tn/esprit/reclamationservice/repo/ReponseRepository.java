package tn.esprit.reclamationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {
    Reponse findByReclamationIdReclamation(Long reclamationId);

}