package tn.esprit.reclamationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.service.ReclamationInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
public class ReclamationController {

    @Autowired
    ReclamationInterface reclamationInterface;
    @PostMapping(value = "/addReclamation", consumes = "multipart/form-data")
    public Reclamation addReclamation(@RequestPart("reclamation") String reclamation, @RequestPart("pieceJointe") MultipartFile pieceJointe) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Reclamation reclamationObj = mapper.readValue(reclamation, Reclamation.class);

            // Generate a unique file name to avoid overwriting
            String originalFileName = StringUtils.cleanPath(pieceJointe.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            // Create the full path to save the file
            Path filePath = Paths.get("reclamation-service/uploads/"+ uniqueFileName);

            // Write the file content to the path
            Files.write(filePath, pieceJointe.getBytes());

            // Set the file name in the reclamation object (if needed)
            reclamationObj.setPieceJointe(uniqueFileName);
            reclamationInterface.addReclamation(reclamationObj, pieceJointe);
            return reclamationObj;
        } catch (IOException e) {
            // Handle any exceptions, e.g., file write errors
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getReclamations")
    public List<Reclamation> getReclamations(){
        return  reclamationInterface.getReclamations();
    }
    @DeleteMapping("/deleteReclamation/{id}")
    public void deleteReclamation(@PathVariable Long id){
        reclamationInterface.deleteReclamation(id);
    }


}
