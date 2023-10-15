package tn.esprit.reclamationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.repo.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService implements ReclamationInterface {

    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public Reclamation addReclamation(Reclamation reclamation){
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public void deleteReclamation(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id).get();
        if (reclamation.isEtat()) {
            reclamationRepository.delete(reclamation);
        }
    }


}
