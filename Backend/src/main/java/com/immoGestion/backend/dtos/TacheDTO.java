package com.immoGestion.backend.dtos;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.TypeTache;

public record TacheDTO<StatutTache>(
        Long id,
        String description,
        String categorie,
        TypeTache typeTache,
        StatusTache statut,
        Long logementId,
        Long locataireId
){

}
