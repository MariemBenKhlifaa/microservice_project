package tn.esprit.formationservice.controller;

import com.fasterxml.jackson.core.JacksonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.service.Formationinterface;
import tn.esprit.formationservice.service.InscriptionInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
public class FormationsController {
    @Autowired
    Formationinterface formationinterface;

    @PostMapping("/addFormation")
    public void addFormation(@RequestParam("file") MultipartFile file, @RequestParam("formation")String formation) {
        System.out.println(file);


            formationinterface.AddFormation(formation, file);


    }
    @GetMapping("/listFormation")
    public List<Formation> getallFormation(){
        return  formationinterface.getallFormation();
    }
    @GetMapping("/formation/{id}")
    public Formation findFormation(@PathVariable Long id){

        return formationinterface.findFormation(id);
    }
    @PutMapping("/updateFormation/{id}")
    public Formation updateFormation (@RequestBody Formation e,@PathVariable Long id){
        System.out.println(e);
       return formationinterface.updateFormation(e,id);
    }
    @DeleteMapping("/deleteFormation/{id}")
    public void deleteFormation(@PathVariable Long id){
        formationinterface.deleteFormation(id);
    }

}
