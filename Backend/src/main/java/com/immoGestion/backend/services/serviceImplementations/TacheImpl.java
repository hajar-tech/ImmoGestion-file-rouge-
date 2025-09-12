package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.Enums.StatusTache;
import com.immoGestion.backend.Enums.TypeTache;
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

import java.util.List;

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
        System.out.println(tache.toString());

        tache.setLogement(logement);
        tache.setLocataire(locataire);

        tache.setStatut(StatusTache.EN_ATTENTE);

        Tache saved = tacheRepository.save(tache);
        return tacheMapper.toDTO(saved);

    }

    @Override
    public List<TacheDTO> getIncidentsByLocataire(Long locataireId) {
        List<Tache> incidents = tacheRepository.findByLocataireIdAndTypeTache(locataireId, TypeTache.INCIDENT);
        return incidents.stream()
                .map(tacheMapper::toDTO)
                        .toList();
    }

    @Override
    public List<TacheDTO> getAllTaches() {
        List<Tache> taches = tacheRepository.findAll();
      return taches.stream()
              .map(tache -> tacheMapper.toDTO(tache))
              .toList();
    }
}
