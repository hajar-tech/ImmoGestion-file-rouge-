package com.immoGestion.backend.repositories;

import com.immoGestion.backend.Enums.TypeTache;
import com.immoGestion.backend.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache , Long> {
    List<Tache> findByLocataireIdAndTypeTache(Long locataireId, TypeTache typeTache);

    @Query("select count (t.id) from Tache t")
    int getTotalTache();

//    // incidents d’un locataire dans un logement précis
//    List<Tache> findByLocataireIdAndLogementIdLogement(Long locataireId, Long logementId);
//
//    // toutes les tâches par logement
//    List<Tache> findByLogementIdLogement(Long logementId);
}
