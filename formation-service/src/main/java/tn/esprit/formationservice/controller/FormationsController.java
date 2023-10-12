package tn.esprit.formationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.service.Formationinterface;

import java.util.List;

@RestController
public class FormationsController {
    @Autowired
    Formationinterface formationinterface;
    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation formation){
      return  formationinterface.AddFormation(formation);
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
