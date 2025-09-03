package com.immoGestion.backend.repositories;

import com.immoGestion.backend.models.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocataireRepository extends JpaRepository<Locataire , Long> {
    Optional<Locataire> findByLogement_IdLogement(Long logementId);
}
