package com.immoGestion.backend.dtos;

public record LogementDTO(
        Long idLogement,
       String numeroAppartement,
        int etageNumber,
        Double surface,
        Double prix,
        String type,
        String statut

) {
}
