package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.mapper.LogementViewAdminMapper;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogementImpl implements LogementService {

    private final LogementRepository logementRepository;
    private final LogementViewAdminMapper logementViewAdminMapper;

    public LogementImpl(LogementRepository logementRepository , LogementViewAdminMapper logementViewAdminMapper){
        this.logementRepository = logementRepository;
        this.logementViewAdminMapper = logementViewAdminMapper;
    }

    @Override
    public String creerLogement(Logement logement) {
         logementRepository.save(logement);
        return "logement créer avec success.";
    }

    @Override
    public List<LogementViewAdmin> afficherToutLogements() {
       List<Logement> logements=  logementRepository.findAll();
       return logementViewAdminMapper.ToDto(logements);
    }
}
