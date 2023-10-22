package tn.esprit.reclamationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.reclamationservice.entity.Categorie;
import tn.esprit.reclamationservice.entity.Evaluation;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.entity.Reponse;
import tn.esprit.reclamationservice.service.ReclamationInterface;
import tn.esprit.reclamationservice.service.ReponseInterface;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
public class ReclamationController {

    @Autowired
    private ReclamationInterface reclamationInterface;

    @Autowired
    private ReponseInterface reponseInterface;

    @PostMapping(value = "/addReclamation", consumes = "multipart/form-data")
    public ResponseEntity<Void> addReclamation(@RequestPart("reclamation") String reclamation, @RequestParam(value = "pieceJointe", required = false) MultipartFile pieceJointe) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Reclamation reclamationObj = mapper.readValue(reclamation, Reclamation.class);

            if (pieceJointe != null) {
                String originalFileName = StringUtils.cleanPath(pieceJointe.getOriginalFilename());
                String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                Path filePath = Paths.get("reclamation-service/uploads/" + uniqueFileName);
                Files.write(filePath, pieceJointe.getBytes());
                reclamationObj.setPieceJointe(uniqueFileName);
            }

            // Always save the reclamationObj to the database
            reclamationInterface.addReclamation(reclamationObj, pieceJointe);

            // Return a response with 201 Created status and location header
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reclamationObj.getIdReclamation())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (IOException e) {
            // Handle any exceptions, e.g., file write errors, and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getReclamations")
    public List<Reclamation> getReclamations(){
        return  reclamationInterface.getReclamations();
    }
    @GetMapping("/getReclamationsTraitees")
    public List<Reclamation> getReclamationsTraitees(){
        return  reclamationInterface.getReclamationsTraitees();
    }
    @GetMapping("/getReclamationsNonTraitees")
    public List<Reclamation> getReclamationsNonTraitees(){
        return  reclamationInterface.getReclamationsNonTraitees();
    }

    @DeleteMapping("/deleteReclamation/{id}")
    public void deleteReclamation(@PathVariable Long id){
        reclamationInterface.deleteReclamation(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Reclamation>> filterReclamations(
            @RequestParam(required = false) Evaluation evaluation,
            @RequestParam(required = false) Categorie categorie) {

        List<Reclamation> filteredReclamations = reclamationInterface.filterReclamations(evaluation, categorie);

        return ResponseEntity.ok(filteredReclamations);
    }

    @PostMapping(value = "/addReponse/{idRec}", consumes = "multipart/form-data")
    public Reponse addReponse(@PathVariable Long idRec, @RequestPart("reponse") String reponse, @RequestPart("pieceJointe") MultipartFile pieceJointe) throws IOException {

        try {

            ObjectMapper mapper = new ObjectMapper();
            Reponse reponseObj = mapper.readValue(reponse, Reponse.class);

            // Generate a unique file name to avoid overwriting
            String originalFileName = StringUtils.cleanPath(pieceJointe.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            // Create the full path to save the file
            Path filePath = Paths.get("reclamation-service/uploads/"+ uniqueFileName);

            // Write the file content to the path
            Files.write(filePath, pieceJointe.getBytes());

            // Set the file name in the reclamation object (if needed)
            reponseObj.setPieceJointe(uniqueFileName);
            Reponse rep = reponseInterface.addReponse(idRec, reponseObj, pieceJointe);
            return reponseObj;
        } catch (IOException e) {
            // Handle any exceptions, e.g., file write errors
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getReponse/{idReclamaion}")
    public Reponse getReponse(@PathVariable Long idReclamaion){
        return  reponseInterface.getReponse(idReclamaion);
    }



}
