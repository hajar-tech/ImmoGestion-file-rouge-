package com.immoGestion.backend.models;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.TypeTache;
import jakarta.persistence.*;

@Entity
public class Tache {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;

  private String description;

  private String categorie;

  @Enumerated(EnumType.STRING)
  private TypeTache typeTache;

  @Enumerated(EnumType.STRING)
  private StatusTache statut = StatusTache.EN_ATTENTE;

  @ManyToOne
  private Employe employe;

  @ManyToOne
  private Logement logement;

  @ManyToOne
  private Locataire locataire;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public TypeTache getTypeTache() {
    return typeTache;
  }

  public void setTypeTache(TypeTache typeTache) {
    this.typeTache = typeTache;
  }

  public StatusTache getStatut() {
    return statut;
  }

  public void setStatut(StatusTache statut) {
    this.statut = statut;
  }

  public Employe getEmploye() {
    return employe;
  }

  public void setEmploye(Employe employe) {
    this.employe = employe;
  }

  public Logement getLogement() {
    return logement;
  }

  public void setLogement(Logement logement) {
    this.logement = logement;
  }

  public Locataire getLocataire() {
    return locataire;
  }

  public void setLocataire(Locataire locataire) {
    this.locataire = locataire;
  }
}
