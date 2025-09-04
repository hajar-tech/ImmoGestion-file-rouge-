package com.immoGestion.backend.services.securityService;

import com.immoGestion.backend.models.Utilisateur;
import com.immoGestion.backend.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository= userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable !"));

        String role = "ROLE_" + user.getClass().getSimpleName().toUpperCase();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );

    }
}
