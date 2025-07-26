package com.immoGestion.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("LOCATAIRE")
public class Locataire extends Utilisateur{

    private String situationFamiliale;

    @OneToMany(mappedBy = "locataire")
    private List<Paiement> paiemets;

    @OneToOne // This is the owning side
    @JoinColumn(name = "logement_id_logement", referencedColumnName = "id_logement")
    private Logement logement;

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public List<Paiement> getPaiemets() {
        return paiemets;
    }

    public void setPaiemets(List<Paiement> paiemets) {
        this.paiemets = paiemets;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }
}
