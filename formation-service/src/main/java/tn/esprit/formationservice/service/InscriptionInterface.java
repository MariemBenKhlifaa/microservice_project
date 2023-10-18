package tn.esprit.formationservice.service;

import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.entity.Inscription;

import java.util.List;

public interface InscriptionInterface {
    public List<Inscription> getallInscription();
    public Inscription addInscription(Long IdFormation,Long IdUser,Inscription inscription);
    public Inscription updateInscription(Inscription inscription,Long id);
    public void deleteInscription(Long id);
}
