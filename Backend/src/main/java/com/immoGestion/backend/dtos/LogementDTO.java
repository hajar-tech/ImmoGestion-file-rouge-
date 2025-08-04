package com.immoGestion.backend.dtos;

import java.util.List;

public record LogementDTO(
        Long idLogement,
       String numeroAppartement,
        int etageNumber,
        Double surface,
        Double prix,
        String type,
        String statut,
        String description,
        List<String> imageUrls

) {
}
