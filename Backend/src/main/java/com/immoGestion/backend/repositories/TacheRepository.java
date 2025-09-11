package com.immoGestion.backend.repositories;

import com.immoGestion.backend.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache , Long> {
}
