package tn.esprit.formationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.formationservice.entity.Inscription;
@Repository
public interface InscriptionRepo extends JpaRepository<Inscription,Long> {
}
