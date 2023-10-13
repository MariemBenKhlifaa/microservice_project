package com.example.projetservice.service;

import com.example.projetservice.entity.Condidature;

import java.util.List;

public interface CondidatureInterface {
    public List<Condidature> getallCondidature();
    public Condidature AddPCondidature(Long projetId, Condidature condidature);
    public Condidature findCondidature(Long id);
    public void deleteCondidature(Long id);
}
