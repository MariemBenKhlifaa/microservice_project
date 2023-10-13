package com.example.projetservice.controller;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.entity.Projet;
import com.example.projetservice.service.CondidatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CondidatureController {



    @Autowired
    private CondidatureInterface condidatureInterface;

    @PostMapping("/condidate/{projetId}")
    public Condidature addCondidatureToProjet(@PathVariable Long projetId, @RequestBody Condidature condidature) {
        return condidatureInterface.AddPCondidature(projetId, condidature);
    }
    @GetMapping("/listCondid")
    public List<Condidature> getallCondidature(){
        return  condidatureInterface.getallCondidature();
    }
    @GetMapping("/findc/{id}")

    public Condidature findCondidature(@PathVariable Long id){

        return condidatureInterface.findCondidature(id);
    }

    @DeleteMapping("/deleteCondidature/{id}")
    public void deleteCondidature(@PathVariable Long id){
        condidatureInterface.deleteCondidature(id);
    }


}
