package tn.esprit.formationservice.service;

import tn.esprit.formationservice.entity.Formation;

import java.util.List;

public interface Formationinterface {
    public List<Formation> getallFormation();
    public Formation AddFormation(Formation e);
    public Formation findFormation(Long id);
    public Formation updateFormation(Formation e,Long id);
    public void deleteFormation(Long id);
}
