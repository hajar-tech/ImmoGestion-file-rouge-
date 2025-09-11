package com.immoGestion.backend.services.serviceInterfaces;

import com.immoGestion.backend.dtos.TacheDTO;
import org.springframework.stereotype.Service;

@Service
public interface TacheService {

    TacheDTO createTache(TacheDTO dto);
}
