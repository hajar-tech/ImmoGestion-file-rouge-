package com.immoGestion.backend.dtos;

import com.immoGestion.backend.Enums.TypeLogement;

import java.util.List;

public record LogementDTO(
        Long idLogement,
       String numeroAppartement,
        int etageNumber,
        Double surface,
        Double prix,
        TypeLogement type,
        String statut,
        String description,
        List<String> imageUrls

) {
}
