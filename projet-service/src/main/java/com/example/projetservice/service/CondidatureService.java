package com.example.projetservice.service;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.entity.Projet;
import com.example.projetservice.repo.CondidatureRepo;
import com.example.projetservice.repo.ProjetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.TypedQuery;
import java.util.List;
@Service

public class CondidatureService implements  CondidatureInterface{

    @Autowired
    CondidatureRepo condidatureRepo;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProjetRepo projetRepo;

    @Override
    public List<Condidature> getallCondidature() {
        return condidatureRepo.findAll();
    }

    @Override
    public Condidature AddPCondidature(Long projetId, Condidature condidature,MultipartFile pieceJointe) {
        Projet projet = projetRepo.findById(projetId).orElseThrow(() -> new RuntimeException("Projet not found")); // Vérifiez l'existence du projet

        condidature.setProjet(projet);

        return condidatureRepo.save(condidature);

    }

    @Override
    public Condidature findCondidature(Long id) {
        return condidatureRepo.findById(id).get();
    }



    @Override
    public void deleteCondidature(Long id) {
        Condidature condidature = condidatureRepo.findById(id).get();
        condidatureRepo.delete(condidature);

    }

    public List<Condidature> getCondidaturesByProjetId(Long projectId) {
        String jpql = "SELECT c FROM Condidature c WHERE c.projet.ProjetId = :projectId";
        TypedQuery<Condidature> query = entityManager.createQuery(jpql, Condidature.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();
    }

}
