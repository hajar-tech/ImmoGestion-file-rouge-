package com.immoGestion.backend.models;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.Enums.TypeLogement;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Logement {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logement")
    private Long idLogement;
    private String numeroAppartement;
    private int etageNumber;
    private Double surface;
    private Double prix;

    private String description; // NOUVEAU : description détaillée

    @ElementCollection // Permet de stocker une collection de types simples
    @CollectionTable(name = "logement_image_urls", joinColumns = @JoinColumn(name = "id_logement"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Enumerated(EnumType.STRING)
    private TypeLogement type;

    @Enumerated(EnumType.STRING)
    private StatutLogement statut = StatutLogement.LIBRE;

    @OneToMany(mappedBy = "logement")
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "logement")
    private List<Charge> charges;

    @OneToOne(mappedBy = "logement" , cascade=CascadeType.ALL, orphanRemoval=true)
    private Locataire locataire;



    public Long getIdLogement() {
        return idLogement;
    }

    public void setIdLogement(Long idLogement) {
        this.idLogement = idLogement;
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

    public TypeLogement getType() {
        return type;
    }

    public void setType(TypeLogement type) {
        this.type = type;
    }

    public StatutLogement getStatut() {
        return statut;
    }

    public void setStatut(StatutLogement statut) {
        this.statut = statut;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
