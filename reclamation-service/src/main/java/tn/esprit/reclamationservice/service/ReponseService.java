package tn.esprit.reclamationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;
import tn.esprit.reclamationservice.repo.ReclamationRepository;
import tn.esprit.reclamationservice.repo.ReponseRepository;

import java.util.List;

@Service
public class ReponseService implements ReponseInterface {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    @Override
    public Reponse addReponse(Long idReclamation, Reponse reponse, MultipartFile pieceJointe) {
        Reclamation reclamation = reclamationRepository.findById(idReclamation).orElseThrow(() -> new RuntimeException("reclamation not found"));
        reponse.setReclamation(reclamation);
        return reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> getReponse() {
        return null;
    }
}
