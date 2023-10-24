package tn.esprit.formationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.entity.Inscription;
import tn.esprit.formationservice.repo.FormationRepo;
import tn.esprit.formationservice.repo.InscriptionRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class InscriptionService implements InscriptionInterface{
   @Autowired
    InscriptionRepo inscriptionRepo;
   @Autowired
    FormationRepo formationRepo;

    @Override
    public List<Inscription> getallInscription() {
        return inscriptionRepo.findAll();
    }

    @Override

    public Inscription addInscription(Long IdFormation, Long IdUser, Inscription inscription) {

            Formation formation = formationRepo.findById(IdFormation).get();
        inscription.setFormation(formation);
        inscription.setIdUser(IdUser);

        // Ajoutez l'inscription à la liste des inscriptions de la formation
        if (formation.getInscriptions() == null) {
            formation.setInscriptions(new ArrayList<>());
        }
        formation.getInscriptions().add(inscription);

        // Sauvegardez la formation avec les inscriptions associées
        formationRepo.save(formation);

        return inscription;


    }

    @Override
    public Inscription updateInscription(Inscription inscription, Long id) {
        Inscription inscri = inscriptionRepo.findById(id).get();
        if (inscription.getNom() != null) {
            inscri.setNom(inscription.getNom());
        }   if (inscription.getPrenom() != null) {
            inscri.setPrenom(inscription.getPrenom());
        }
        if (inscription.getEmail() != null) {
            inscri.setEmail(inscription.getEmail());
        }

        inscriptionRepo.save(inscri);

        return inscri;
    }

    @Override
    public void deleteInscription(Long id) {
        Inscription inscri = inscriptionRepo.findById(id).get();
        inscriptionRepo.delete(inscri);

    }
}
