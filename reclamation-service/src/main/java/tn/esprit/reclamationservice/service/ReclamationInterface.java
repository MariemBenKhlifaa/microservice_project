package tn.esprit.reclamationservice.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;

import java.util.List;

public interface ReclamationInterface {

    public Reclamation addReclamation(Reclamation reclamation, MultipartFile pieceJointe);

    public List<Reclamation> getReclamations();

    public void deleteReclamation(Long id);
}