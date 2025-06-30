package com.immoGestion.backend.dtos;

public record LogementViewAdmin(
        Long id,
        String numeroLogement,
        int numeroEtage,
        double surface ,
        double prix,
        String typeLogement,
        String statutLogement,

        String nomLocataire,
        String contactLocataire,
        int paiementNombre,
        int chargeNombre
) {
}
