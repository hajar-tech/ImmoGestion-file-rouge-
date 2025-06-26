package com.immoGestion.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datePaiement;
    private Double montantPaiement;
    private String moyenPaiement;

    @ManyToOne
    private Locataire locataire;

    @ManyToOne
    private Logement logement;
}
