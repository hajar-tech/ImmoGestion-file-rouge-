package com.immoGestion.backend.dtos;

import com.immoGestion.backend.Enums.TypeLogement;

public class LocataireDetailDTO {


    private String prenom;
    private String nom;
    private String numeroTelephone;
    private String carteIdentite;
    private String email;
    private String password; // pour création/modification
    private String situationFamiliale;

    // Logement associé (infos principales)

   private String numeroAppartement;
  private  int etageNumber;
   private Double surface;
   private Double prix;
   private TypeLogement type;


   //getters and setters

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

    public String getNumeroAppartement() {
        return numeroAppartement;
    }

    public void setNumeroAppartement(String numeroAppartement) {
        this.numeroAppartement = numeroAppartement;
    }

    public int getEtageNumber() {
        return etageNumber;
    }

    public void setEtageNumber(int etageNumber) {
        this.etageNumber = etageNumber;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public TypeLogement getType(TypeLogement type) {
        return this.type;
    }

    public void setType(TypeLogement type) {
        this.type = type;
    }
}
