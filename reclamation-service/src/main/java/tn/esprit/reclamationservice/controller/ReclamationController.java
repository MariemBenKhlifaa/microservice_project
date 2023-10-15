package tn.esprit.reclamationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.reclamationservice.entity.Reclamation;
import tn.esprit.reclamationservice.service.ReclamationInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ReclamationController {

    @Autowired
    ReclamationInterface reclamationInterface;
    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/addReclamation")
    public Reclamation addReclamation (@RequestBody Reclamation reclamation)  {
        return  reclamationInterface.addReclamation(reclamation);
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
