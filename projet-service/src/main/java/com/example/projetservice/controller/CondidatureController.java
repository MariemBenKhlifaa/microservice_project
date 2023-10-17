package com.example.projetservice.controller;


import com.example.projetservice.entity.Condidature;
import com.example.projetservice.service.CondidatureInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.example.projetservice.config.FileStorageConfig.UPLOAD_DIR;


@RestController

public class CondidatureController {



    @Autowired
    private CondidatureInterface condidatureInterface;


// ...
@PostMapping(value = "/condidate/{projetId}", consumes = "multipart/form-data")
public ResponseEntity<Condidature> addCondidatureToProjet(@PathVariable Long projetId,
                                                          @RequestPart("condidature") String condidature,
                                                          @RequestPart("pieceJointe") MultipartFile pieceJointe) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        Condidature condidatureobj = mapper.readValue(condidature, Condidature.class);

        String originalFileName = StringUtils.cleanPath(pieceJointe.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);

        // Ensure directory exists.
        Files.createDirectories(filePath.getParent());

        Files.write(filePath, pieceJointe.getBytes());

        condidatureobj.setPieceJointe(uniqueFileName);
        condidatureInterface.AddPCondidature(projetId, condidatureobj, pieceJointe);

        return new ResponseEntity<>(condidatureobj, HttpStatus.CREATED);
    } catch (IOException e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (MalformedURLException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
