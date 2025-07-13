package com.immoGestion.backend.dtos;

public record RegisterRequest(
        String nom,
        String prenom,
        String numeroTelephone,
        String carteIdentite,
        String email,
        String password,
        String role

) {
}
