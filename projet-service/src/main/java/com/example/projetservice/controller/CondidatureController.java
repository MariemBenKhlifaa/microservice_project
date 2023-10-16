package com.example.projetservice.controller;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.entity.CondidatureDto;
import com.example.projetservice.service.CondidatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController

public class CondidatureController {



    @Autowired
    private CondidatureInterface condidatureInterface;


// ...

    @PostMapping("/condidate/{projetId}")
    public ResponseEntity<?> addCondidatureToProjet(@PathVariable Long projetId, @RequestBody CondidatureDto condidatureDto) {
        if (condidatureDto.getCvBase64() == null || !isBase64Valid(condidatureDto.getCvBase64())) {
            return new ResponseEntity<String>("Invalid CV Base64 data", HttpStatus.BAD_REQUEST);
        }

        byte[] cvBytes = Base64.getDecoder().decode(condidatureDto.getCvBase64());

        // Sauvegarder le CV en tant que fichier PDF
        Path cvPath = Paths.get("C:\\Users\\user\\Desktop\\webdistAngular\\src\\uploads", "cv_" + projetId + ".pdf");
        try {
            Files.write(cvPath, cvBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error saving CV to file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Condidature condidature = new Condidature();

        // Set the decoded CV bytes - Assuming setCv() sets a path or something similar
        condidature.setCv(cvPath.toString());

        // Copy other fields from DTO to the entity
        condidature.setNom(condidatureDto.getNom());
        condidature.setPrenom(condidatureDto.getPrenom());
        condidature.setEmail(condidatureDto.getEmail());
        condidature.setTel(condidatureDto.getTel());
        condidature.setLettreMotivation(condidatureDto.getLettreMotivation());

        // Assuming the AddPCondidature method takes a project ID and Condidature entity and returns a Condidature.
        Condidature savedCondidature = condidatureInterface.AddPCondidature(projetId, condidature);

        return new ResponseEntity<Condidature>(savedCondidature, HttpStatus.CREATED);
    }



    private boolean isBase64Valid(String base64) {
        try {
            // Try decoding
            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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
