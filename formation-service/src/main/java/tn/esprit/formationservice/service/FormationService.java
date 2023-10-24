package tn.esprit.formationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.formationservice.entity.Formation;
import tn.esprit.formationservice.repo.FormationRepo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FormationService implements Formationinterface {
    @Autowired
FormationRepo formationRepo;



    @Override
    public List<Formation> getallFormation() {
        return formationRepo.findAll();
    }

    @Override
    public Formation AddFormation(String formationData, MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Formation formation = objectMapper.readValue(formationData, Formation.class);

            String fileName = file.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") + "/uploadd/";
            String filePath = Paths.get(uploadDir, fileName).toString();
            Files.createDirectories(Paths.get(uploadDir)); // Assurez-vous que le répertoire existe
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            formation.setImage(fileName);
            formationRepo.save(formation);
            return formation;
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }



    @Override
    public Formation findFormation(Long id) {

        return formationRepo.findById(id).get();
    }

    @Override
    public Formation updateFormation(Formation e,Long id) {
       Formation formation = formationRepo.findById(id).get();
       if(e.getTitre()!=null){
           formation.setTitre(e.getTitre());
       }
           if(e.getImage()!=null){
               formation.setImage(e.getImage());
           }
           if (e.getDescription() !=null){
               formation.setDescription(e.getDescription());
           }
        if (e.getDate_debut() !=null){
            formation.setDate_debut(e.getDate_debut());
        }
        if (e.getDate_fin() !=null){
            formation.setDate_fin(e.getDate_fin());
        }
        if (e.getPrix() !=null){
            formation.setPrix(e.getPrix());
        }


        formationRepo.save(formation);

        return formation;
    }

    @Override
    public void deleteFormation(Long id) {
        Formation formation = formationRepo.findById(id).get();
        formationRepo.delete(formation);
    }
}
