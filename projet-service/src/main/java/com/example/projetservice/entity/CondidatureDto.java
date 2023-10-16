package com.example.projetservice.entity;
public class CondidatureDto {

    private Long idCondidature;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String cvBase64;  // Pour recevoir le CV en base64
    private String lettreMotivation;

    // Si vous avez besoin d'autres champs liés à Projet, vous pouvez les ajouter ici.

    // Constructeurs, getters et setters

    public CondidatureDto() {}

    public Long getIdCondidature() {
        return idCondidature;
    }

    public void setIdCondidature(Long idCondidature) {
        this.idCondidature = idCondidature;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCvBase64() {
        return cvBase64;
    }

    public void setCvBase64(String cvBase64) {
        this.cvBase64 = cvBase64;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    // Vous pouvez également ajouter des méthodes `equals`, `hashCode`, et `toString` si nécessaire.
}
