package com.immoGestion.backend.services.securityService;

import com.immoGestion.backend.models.Admin;
import com.immoGestion.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        String adminEmail = "admin@enaaskills.com";  // email admin par défaut

        if (!userRepository.existsByEmail(adminEmail)) {

            Admin admin = new Admin();  // instancie la sous-classe Admin

            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("Admin123!"));
            // Ajoute autres champs si besoin

            userRepository.save(admin);
            System.out.println("Admin créé automatiquement.");
        } else {
            System.out.println("Admin déjà présent en base.");
        }
    }
}
