package com.example.projetservice.service;

import com.example.projetservice.entity.Projet;

import java.util.List;

public interface ProjetInterface {

    public List<Projet> getallProjet();
    public Projet AddProjet(Projet p);
    public Projet findProjet(Long id);
    public Projet updateProjet(Projet p, Long id);
    public void deleteProjet(Long id);
}
