package tn.esprit.formationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.formationservice.entity.Inscription;
import tn.esprit.formationservice.service.InscriptionInterface;

import java.util.List;

@RestController
public class InscriptionController {
    @Autowired
    InscriptionInterface inscriptionInterface;
    @GetMapping("/getallInscri")
    public List<Inscription> getallInscription(){
        return inscriptionInterface.getallInscription();
    }
    @PostMapping("/inscriver/{IdFormation}/{IdUser}")
    public Inscription addInscription(@PathVariable Long IdFormation,@PathVariable  Long IdUser,@RequestBody Inscription inscription){
        return inscriptionInterface.addInscription(IdFormation,IdUser,inscription);
    }
    @PutMapping("/updateInscri/{id}")
    public Inscription updateInscription(@RequestBody Inscription inscription,@PathVariable Long id){
        return inscriptionInterface.updateInscription(inscription,id);
    }
    @DeleteMapping("/deleteInscri/{id}")
    public void deleteInscription(@PathVariable Long id){
        inscriptionInterface.deleteInscription(id);
    }
}
