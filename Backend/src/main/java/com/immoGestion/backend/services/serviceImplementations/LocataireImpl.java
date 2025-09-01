package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.mapper.LocataireMapper;
import com.immoGestion.backend.models.Locataire;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.repositories.LocataireRepository;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.services.serviceInterfaces.LocataireService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocataireImpl implements LocataireService {

    private final LocataireRepository locataireRepository;
    private final LocataireMapper locataireMapper;
    private final PasswordEncoder passwordEncoder;
    private final LogementRepository logementRepository;

    public LocataireImpl(LocataireRepository locataireRepository,
                         LocataireMapper locataireMapper,
                         PasswordEncoder passwordEncoder,
                         LogementRepository logementRepository) {
        this.locataireRepository = locataireRepository;
        this.locataireMapper = locataireMapper;
        this.passwordEncoder = passwordEncoder;
        this.logementRepository = logementRepository;
    }


    @Override
    public LocataireDTO creerLocataire(LocataireDTO dto) {
        Locataire locataire = locataireMapper.toEntity(dto);
        Locataire saved = locataireRepository.save(locataire);
        return locataireMapper.toDto(saved);
    }

    @Override
    public LocataireDTO modifierLocataire(Long id, LocataireDTO dto) {

        return locataireRepository.findById(id).map(existing -> {
            locataireMapper.updateLocataireFromDto(dto, existing);
            return locataireMapper.toDto(locataireRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Locataire introuvable"));

    }

    @Override
    public void supprimerLocataire(Long id) {
        Locataire locataire = locataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locataire introuvable"));
        locataire.setLogement(null); // dissocier le logement pour éviter FK
        locataireRepository.delete(locataire);}

    @Override
    public List<LocataireDTO> getAllLocataires() {
        return locataireRepository.findAll()
                .stream()
                .map(locataireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LocataireDTO> getLocataireById(Long id) {
        return locataireRepository.findById(id).map(locataireMapper::toDto);
    }
}
