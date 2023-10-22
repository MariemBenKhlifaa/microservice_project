package tn.esprit.reclamationservice.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Categorie;
import tn.esprit.reclamationservice.entity.Evaluation;
import tn.esprit.reclamationservice.entity.Reclamation;

import java.util.List;

public interface ReclamationInterface {

    public Reclamation addReclamation(Reclamation reclamation, MultipartFile pieceJointe);

    public List<Reclamation> getReclamationsTraitees();

    List<Reclamation> getReclamations();

    public List<Reclamation> getReclamationsNonTraitees();

    public void deleteReclamation(Long id);

    List<Reclamation> filterReclamations(Evaluation evaluation,Categorie categorie);

}