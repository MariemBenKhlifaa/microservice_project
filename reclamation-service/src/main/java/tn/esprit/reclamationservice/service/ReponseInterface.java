package tn.esprit.reclamationservice.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;

import java.util.List;

public interface ReponseInterface {

    public Reponse addReponse(Long idReclamation, Reponse reponse, MultipartFile pieceJointe);

    public Reponse getReponse(Long reclamationId);

}
