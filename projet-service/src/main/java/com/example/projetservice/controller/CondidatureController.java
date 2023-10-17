package com.example.projetservice.controller;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.service.CondidatureInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController

public class CondidatureController {



    @Autowired
    private CondidatureInterface condidatureInterface;


// ...
@PostMapping(value = "/condidate/{projetId}" ,consumes = "multipart/form-data")
public Condidature addCondidatureToProjet ( @PathVariable Long projetId,@RequestPart("condidature") String condidature, @RequestPart("pieceJointe") MultipartFile pieceJointe) throws IOException {
    try {
        ObjectMapper mapper = new ObjectMapper();
        Condidature condidatureobj = mapper.readValue(condidature, Condidature.class);

        // Generate a unique file name to avoid overwriting
        String originalFileName = StringUtils.cleanPath(pieceJointe.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Create the full path to save the file
        Path filePath = Paths.get("projet-service/uploads/"+ uniqueFileName);

        // Write the file content to the path
        Files.write(filePath, pieceJointe.getBytes());

        // Set the file name in the reclamation object (if needed)
        condidatureobj.setPieceJointe(uniqueFileName);
        condidatureInterface.AddPCondidature(projetId,condidatureobj, pieceJointe);
        return condidatureobj;
    } catch (IOException e) {
        // Handle any exceptions, e.g., file write errors
        throw new RuntimeException(e);
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
