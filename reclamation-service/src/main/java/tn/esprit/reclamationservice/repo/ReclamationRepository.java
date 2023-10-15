package tn.esprit.reclamationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.reclamationservice.entity.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {



}