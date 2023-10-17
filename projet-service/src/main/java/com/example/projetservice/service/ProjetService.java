package com.example.projetservice.service;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.entity.Projet;
import com.example.projetservice.repo.ProjetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService implements ProjetInterface {
    @Autowired
    ProjetRepo projetRepo;


    @Override
    public List<Projet> getallProjet() {


        return projetRepo.findAll();
    }

    @Override
    public Projet AddProjet(Projet p) {
        return projetRepo.save(p);
    }

    @Override
    public Projet findProjet(Long id) {

        return projetRepo.findById(id).get();
    }

    @Override
    public Projet updateProjet(Projet p,Long id) {
        Projet projet = projetRepo.findById(id).get();
        if(p.getNom()!=null){
            projet.setNom(p.getNom());
        }
        if (p.getDescription() !=null){
            projet.setDescription(p.getDescription());
        }
        if (p.getDateProjet() !=null){
            projet.setDateProjet(p.getDateProjet());
        }
        if (p.getPrix() !=null){
            projet.setPrix(p.getPrix());
        }


         return  projetRepo.save(projet);

    }

    @Override
    public void deleteProjet(Long id) {
        Projet projet = projetRepo.findById(id).get();
        projetRepo.delete(projet);
    }



}
