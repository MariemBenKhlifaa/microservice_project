package tn.esprit.formationservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.formationservice.entity.Formation;

@Repository
public interface FormationRepo extends JpaRepository<Formation,Long> {
}
