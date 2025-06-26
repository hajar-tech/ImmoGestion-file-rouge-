package com.immoGestion.backend.models;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.Enums.TypeLogement;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Logement {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroAppartement;
    private int etageNumber;
    private Double surface;
    private Double prix;

    @Enumerated(EnumType.STRING)
    private TypeLogement type;

    @Enumerated(EnumType.STRING)
    private StatutLogement statut;

    @OneToMany(mappedBy = "logement")
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "logement")
    private List<Charge> charges;

    @OneToOne(mappedBy = "logement")
    private Locataire locataire;
}
