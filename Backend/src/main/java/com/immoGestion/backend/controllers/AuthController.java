package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.LoginRequest;
import com.immoGestion.backend.dtos.LoginResponse;
import com.immoGestion.backend.dtos.RegisterRequest;
import com.immoGestion.backend.services.securityService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterRequest registerRequest) {

        try {
            authService.registerUser(registerRequest);
            return ResponseEntity.ok("Utilisateur enregistré avec succès.");
        }catch(Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


}
