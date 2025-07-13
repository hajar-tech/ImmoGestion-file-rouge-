package com.immoGestion.backend.dtos;

public record LoginRequest(
        String email,
        String password
) {
}
