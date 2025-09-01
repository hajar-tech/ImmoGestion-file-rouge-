package com.immoGestion.backend.repositories;

import com.immoGestion.backend.models.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocataireRepository extends JpaRepository<Locataire , Long> {
}
