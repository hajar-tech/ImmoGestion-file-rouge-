package com.immoGestion.backend.services.serviceInterfaces;

import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogementService {

    //ajouter logement
     String creerLogement(Logement logement);

     //afficher la liste des logements au admin
   List<LogementViewAdmin>  afficherToutLogements();
}
