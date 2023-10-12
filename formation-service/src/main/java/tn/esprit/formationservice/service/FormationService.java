package tn.esprit.formationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.repo.FormationRepo;

import java.util.List;

@Service
public class FormationService implements Formationinterface {
    @Autowired
FormationRepo formationRepo;


    @Override
    public List<Formation> getallFormation() {
        return formationRepo.findAll();
    }

    @Override
    public Formation AddFormation(Formation e) {
        return formationRepo.save(e);
    }

    @Override
    public Formation findFormation(Long id) {

        return formationRepo.findById(id).get();
    }

    @Override
    public Formation updateFormation(Formation e,Long id) {
       Formation formation = formationRepo.findById(id).get();
       if(e.getTitre()!=null){
           formation.setTitre(e.getTitre());
       }
           if(e.getImage()!=null){
               formation.setImage(e.getImage());
           }
           if (e.getDescription() !=null){
               formation.setDescription(e.getDescription());
           }
        if (e.getDate_debut() !=null){
            formation.setDate_debut(e.getDate_debut());
        }
        if (e.getDate_fin() !=null){
            formation.setDate_fin(e.getDate_fin());
        }
        if (e.getPrix() !=null){
            formation.setPrix(e.getPrix());
        }


        formationRepo.save(formation);

        return null;
    }

    @Override
    public void deleteFormation(Long id) {
        Formation formation = formationRepo.findById(id).get();
        formationRepo.delete(formation);
    }
}
