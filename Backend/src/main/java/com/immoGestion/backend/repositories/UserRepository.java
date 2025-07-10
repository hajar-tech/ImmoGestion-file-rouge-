package com.immoGestion.backend.repositories;

import com.immoGestion.backend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur , Long> {
    Optional<Utilisateur> findByEmail(String email);
}
