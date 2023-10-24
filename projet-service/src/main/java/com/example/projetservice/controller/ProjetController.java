package com.example.projetservice.controller;
import com.example.projetservice.service.ProjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.projetservice.entity.Projet;
import com.example.projetservice.service.ProjetInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProjetController {
    @Autowired
    private ProjetService projetService;
    @Autowired
    ProjetInterface projetInterface;
    @PostMapping("/addProjet")
    public Projet addProjet (@RequestBody Projet projet){
        return  projetInterface.AddProjet(projet);
    }
    @GetMapping("/listProjet")
    public List<Projet> getallProjet(){
        return  projetInterface.getallProjet();
    }
    @GetMapping("/Projet/{id}")
    public Projet findProjet(@PathVariable Long id){

        return projetInterface.findProjet(id);
    }
    @PutMapping("/updateProjet/{id}")
    public Projet updateProjet (@RequestBody Projet p,@PathVariable Long id){
        System.out.println(p);
        return projetInterface.updateProjet(p,id);
    }
    @DeleteMapping("/deleteProjet/{id}")
    public void deleteProjet(@PathVariable Long id){
        projetInterface.deleteProjet(id);
    }
    @GetMapping("/search/{nom}")
    public ResponseEntity<?> search(@PathVariable String nom) {
        try {
            List<Projet> projets = projetService.searchByNom(nom);
            return new ResponseEntity<>(projets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
