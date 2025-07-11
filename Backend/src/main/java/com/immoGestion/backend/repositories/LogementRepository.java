package com.immoGestion.backend.repositories;

import com.immoGestion.backend.models.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {
}
