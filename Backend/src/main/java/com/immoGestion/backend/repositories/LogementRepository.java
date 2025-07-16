package com.immoGestion.backend.repositories;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.models.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {

    List<Logement> findByStatut(StatutLogement statut);

}
