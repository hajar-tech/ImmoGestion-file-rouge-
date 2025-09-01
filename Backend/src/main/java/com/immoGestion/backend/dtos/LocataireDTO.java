package com.immoGestion.backend.dtos;

public class LocataireDTO {

    private Long id;
    private String prenom;
    private String nom;
    private String numeroTelephone;
    private String carteIdentite;
    private String email;
    private String password; // pour création/modification
    private String situationFamiliale;
   // private Long logementId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getCarteIdentite() {
        return carteIdentite;
    }

    public void setCarteIdentite(String carteIdentite) {
        this.carteIdentite = carteIdentite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

//    public Long getLogementId() {
//        return logementId;
//    }
//
//    public void setLogementId(Long logementId) {
//        this.logementId = logementId;
//    }
}
