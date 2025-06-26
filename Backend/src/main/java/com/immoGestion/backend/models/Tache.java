package com.immoGestion.backend.models;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.CategorieTache;
import com.immoGestion.backend.Enums.TypeTache;
import jakarta.persistence.*;

@Entity
public class Tache {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String titre;
  private String description;

  @Enumerated(EnumType.STRING)
  private CategorieTache categorieTacheTache;

  @Enumerated(EnumType.STRING)
  private TypeTache typeTache;

  @Enumerated(EnumType.STRING)
  private StatusTache statut;

  @ManyToOne
  private Employe employe;

  @ManyToOne
  private Logement logement;
}
