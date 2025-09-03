package com.immoGestion.backend.services.serviceInterfaces;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.dtos.LogementDTO;
import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LogementService {

    //ajouter logement
     LogementDTO creerLogement(LogementDTO logementDTO);

     //afficher la liste des logements au admin
   List<LogementDTO>  getAllLogements();

   Optional<LogementDTO> getLogementById(Long id);

    LogementDTO updateLogement(Long id, LogementDTO logementDTO);

    void deleteLogement(Long id);

    List<LogementDTO> getLogementsByStatus(StatutLogement statut);

    void libererLogement(Long locataireId);
}
