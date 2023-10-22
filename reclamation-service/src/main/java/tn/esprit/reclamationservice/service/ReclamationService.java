package tn.esprit.reclamationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Categorie;
import tn.esprit.reclamationservice.entity.Evaluation;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.repo.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService implements ReclamationInterface {

    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public Reclamation addReclamation(Reclamation reclamation, MultipartFile pieceJointe){
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> getReclamationsNonTraitees() {
        return reclamationRepository.findByEtatFalse();
    }

    @Override
    public List<Reclamation> getReclamationsTraitees() {
        return reclamationRepository.findByEtatTrue();
    }

    @Override
    public void deleteReclamation(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id).get();
            reclamationRepository.delete(reclamation);

    }

    @Override
    public List<Reclamation> filterReclamations(Evaluation evaluation, Categorie categorie) {
        if (evaluation != null && categorie != null) {
            // Filter by both evaluation and categorie
            return reclamationRepository.findByEvaluationAndCategorieAndEtatFalse(evaluation, categorie);
        } else if (evaluation != null) {
            // Filter by evaluation only
            return reclamationRepository.findByEvaluationAndEtatFalse(evaluation);
        } else if (categorie != null) {
            // Filter by categorie only
            return reclamationRepository.findByCategorieAndEtatFalse(categorie);
        } else {
            // No filter criteria provided, return all reclamations
            return reclamationRepository.findByEtatFalse();
        }
    }


}
