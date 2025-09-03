package com.immoGestion.backend.services.serviceInterfaces;

import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.dtos.LocataireDetailDTO;
import com.immoGestion.backend.dtos.LocataireLogementAssociationDTO;
import com.immoGestion.backend.mapper.LocataireMapper;
import com.immoGestion.backend.repositories.LocataireRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocataireService {

    LocataireDTO creerLocataire(LocataireDTO dto);
    LocataireDTO modifierLocataire(Long id, LocataireDTO dto);
     void supprimerLocataire(Long id);
     List<LocataireDTO> getAllLocataires();
     Optional<LocataireDTO> getLocataireById(Long id);
    void assignLogementToLocataire(LocataireLogementAssociationDTO dto);
    LocataireDetailDTO getLocataireDetail(Long locataireId);
    void libererLogement(Long locataireId, Long logementId);
    void dissocierLocataire(Long idLocataire, Long idLogement);




}
