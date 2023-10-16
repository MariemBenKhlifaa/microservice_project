package tn.esprit.reclamationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.reclamationservice.entity.Reclamation;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    List<Reclamation> findByEtatFalse();
    List<Reclamation> findByEtatTrue();


}