package com.immoGestion.backend.repositories;

import com.immoGestion.backend.Enums.ProprieteLogement;
import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.models.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Long> {

    List<Logement> findByStatut(StatutLogement statut);

    List<Logement> findByPropriete(ProprieteLogement propriete);

//    @Query("select count(l.idLogement) from Logement l where l.statut =:statut ")
//    int countLogementLibre (@Param("statut") String statut);
//
//    @Query("select count(l.idLogement) from Logement l where l.aAscenseur")
//    int countLogementHasAscensseur ();
//
//    @Query("select l from Logement l where l.type =:type ")
//    List<Logement> getLogementAppartement(@Param("type") String type);
//
//    @Query("select l from Logement l where l.prix between :prixMin and :prixMax")
//    List<Logement> getLogementByPrixBetweeen(@Param("prixMin") String prixMin , @Param("prixMax") String prixMax);
//
//    //get by Room number
//    @Query("select l.idLogement from Logement l where l.nombreChambre =:number")
//    List<Logement> getLogementByRoomNumber(@Param("number") int numder);
//

}
