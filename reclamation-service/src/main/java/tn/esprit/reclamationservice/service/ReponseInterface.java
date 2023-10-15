package tn.esprit.reclamationservice.service;

import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;

import java.util.List;

public interface ReponseInterface {

    public Reponse addReponse(Reponse reponse);

    public List<Reponse> getReponse();

}
