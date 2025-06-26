package com.immoGestion.backend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
@DiscriminatorValue("LOCATAIRE")
public class Locataire extends Utilisateur{

    private String situationFamiliale;

    @OneToMany(mappedBy = "locataire")
    private List<Paiement> paiemets;

    @OneToOne
    private Logement logement;
}
