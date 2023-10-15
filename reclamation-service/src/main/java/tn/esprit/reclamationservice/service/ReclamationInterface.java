package tn.esprit.reclamationservice.service;

import tn.esprit.reclamationservice.entity.Reclamation;

import java.util.List;

public interface ReclamationInterface {

    public Reclamation addReclamation(Reclamation reclamation);

    public List<Reclamation> getReclamations();

    public void deleteReclamation(Long id);
}