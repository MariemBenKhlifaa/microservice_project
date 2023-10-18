package tn.esprit.formationservice.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.formationservice.entity.Formation;

import java.util.List;

public interface Formationinterface {
    public List<Formation> getallFormation();
    public Formation AddFormation(String formationData, MultipartFile file);
    public Formation findFormation(Long id);
    public Formation updateFormation(Formation e,Long id);
    public void deleteFormation(Long id);
}
