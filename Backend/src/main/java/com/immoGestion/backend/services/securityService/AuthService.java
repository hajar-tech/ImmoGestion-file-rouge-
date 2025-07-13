package com.immoGestion.backend.services.securityService;

import com.immoGestion.backend.dtos.LoginRequest;
import com.immoGestion.backend.dtos.LoginResponse;
import com.immoGestion.backend.dtos.RegisterRequest;
import com.immoGestion.backend.models.Employe;
import com.immoGestion.backend.models.Locataire;
import com.immoGestion.backend.models.Utilisateur;
import com.immoGestion.backend.repositories.UserRepository;
import com.immoGestion.backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService (UserRepository userRepository, PasswordEncoder passwordEncoder , JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(RegisterRequest registerRequest){
        //tester unicité
        if(userRepository.findByEmail(registerRequest.email()).isPresent()){
            throw new RuntimeException("email déjà utilisé !");
        }
        Utilisateur utilisateur;

        //créer user dynamiquement selon le role
        switch (registerRequest.role().toUpperCase()){
            case "EMPLOYE" -> utilisateur = new Employe();
            case "LOCATAIRE" -> utilisateur = new Locataire();
            default -> throw new RuntimeException("Le role est invalide !!");

        }
        utilisateur.setNom(registerRequest.nom());
        utilisateur.setPrenom(registerRequest.prenom());
        utilisateur.setNumeroTelephone(registerRequest.numeroTelephone());
        utilisateur.setCarteIdentite(registerRequest.carteIdentite());
        utilisateur.setEmail(registerRequest.email());
        utilisateur.setPassword(passwordEncoder.encode(registerRequest.password()));

        userRepository.save(utilisateur);
    }

    public LoginResponse login(LoginRequest request){
        Utilisateur utilisateur = userRepository.findByEmail(request.email())
                .orElseThrow(()-> new RuntimeException("Email incorect !"));

        if (!passwordEncoder.matches(request.password(), utilisateur.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String token = jwtUtil.generateToken(utilisateur);
        String role = utilisateur.getClass().getSimpleName().toUpperCase();

        return new LoginResponse(token, utilisateur.getId(),role , utilisateur.getEmail());


    }
}
