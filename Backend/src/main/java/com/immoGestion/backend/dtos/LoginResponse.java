package com.immoGestion.backend.dtos;

public record LoginResponse (
        String token ,
        Long userId,
        String role,
        String email
){
}
