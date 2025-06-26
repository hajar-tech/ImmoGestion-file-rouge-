package com.immoGestion.backend.models;

import jakarta.persistence.*;

import java.time.Year;
import java.time.YearMonth;

@Entity
public class Charge {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeCharge;
    private Double montant;
    private YearMonth mois;
    private Year annee;

    @ManyToOne
    private Logement logement;
}
