package com.immoGestion.backend.dtos;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.TypeTache;

public record TacheAffichageDTO(
        Long id,
        String description,
        String categorie,
        TypeTache typeTache,
        StatusTache statut,
        String nomLocataire,
        String prenomLocataire,
        String numeroAppartement,
        String etage
) {
}
