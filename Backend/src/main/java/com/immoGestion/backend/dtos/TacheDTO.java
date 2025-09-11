package com.immoGestion.backend.dtos;

import com.immoGestion.backend.Enums.CategorieTache;
import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.TypeTache;

public record TacheDTO<StatutTache>(
        Long id,
        String description,
        CategorieTache categorieTache,
        TypeTache typeTache,
        StatusTache statut,
        Long logementId,
        Long locataireId
){

}
