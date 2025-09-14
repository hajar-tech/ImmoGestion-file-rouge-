package com.immoGestion.backend.services.serviceInterfaces;

import com.immoGestion.backend.dtos.TacheAffichageDTO;
import com.immoGestion.backend.dtos.TacheDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TacheService {

    TacheDTO createTache(TacheDTO dto);

    List<TacheDTO> getIncidentsByLocataire(Long locataireId);

    List<TacheAffichageDTO> getAllTaches ();
}
