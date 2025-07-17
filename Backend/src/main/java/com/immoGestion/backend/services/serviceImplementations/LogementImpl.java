package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.dtos.LogementDTO;
import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.mapper.LogementMapper;
import com.immoGestion.backend.mapper.LogementViewAdminMapper;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogementImpl implements LogementService {

    private final LogementRepository logementRepository;
    private final LogementViewAdminMapper logementViewAdminMapper;
    private final LogementMapper logementMapper;
    public LogementImpl(LogementMapper logementMapper ,LogementRepository logementRepository , LogementViewAdminMapper logementViewAdminMapper){
        this.logementRepository = logementRepository;
        this.logementViewAdminMapper = logementViewAdminMapper;
        this.logementMapper = logementMapper;
    }

    @Override
    public LogementDTO creerLogement(LogementDTO logementDTO) {
      Logement logement = logementRepository.save( logementMapper.toEntity(logementDTO));
      return logementMapper.toDto(logement);
    }

    @Override
    public List<LogementDTO> getAllLogements() {
        return logementRepository.findAll().stream()
                .map(logement -> logementMapper.toDto(logement))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LogementDTO> getLogementById(Long id) {
        return Optional.empty();
    }

    @Override
    public LogementDTO updateLogement(Long id, LogementDTO logementDTO) {
        return null;
    }

    @Override
    public void deleteLogement(Long id) {

    }

    @Override
    public List<LogementDTO> getLogementsByStatus(String statut) {
        return List.of();
    }

//    @Override
//    public List<LogementViewAdmin> afficherToutLogements() {
//       List<Logement> logements=  logementRepository.findAll();
//       return logementViewAdminMapper.ToDto(logements);
//    }
}
