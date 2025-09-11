package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.dtos.TacheDTO;
import com.immoGestion.backend.mapper.TacheMapper;
import com.immoGestion.backend.models.Locataire;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.models.Tache;
import com.immoGestion.backend.repositories.LocataireRepository;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.repositories.TacheRepository;
import com.immoGestion.backend.services.serviceInterfaces.TacheService;
import org.springframework.stereotype.Service;

@Service
public class TacheImpl implements TacheService {

    private final TacheRepository tacheRepository;
    private final LogementRepository logementRepository;
    private final LocataireRepository locataireRepository;
    private final TacheMapper tacheMapper;

    public TacheImpl(TacheRepository tacheRepository,
                     LogementRepository logementRepository,
                     LocataireRepository locataireRepository,
                     TacheMapper tacheMapper) {
        this.tacheRepository = tacheRepository;
        this.logementRepository = logementRepository;
        this.locataireRepository = locataireRepository;
        this.tacheMapper = tacheMapper;
    }

    @Override
    public TacheDTO createTache(TacheDTO dto) {

        Logement logement = logementRepository.findById(dto.logementId())
                .orElseThrow(()-> new RuntimeException("Logement introuvable!"));

        Locataire locataire = locataireRepository.findById(dto.locataireId())
                .orElseThrow(()-> new RuntimeException("Locataire introuvbale!"));


        Tache tache = tacheMapper.toEntity(dto);

        tache.setLogement(logement);
        tache.setLocataire(locataire);

        tache.setStatut(StatusTache.EN_ATTENTE);

        Tache saved = tacheRepository.save(tache);
        return tacheMapper.toDTO(saved);

    }
}
