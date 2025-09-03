package com.immoGestion.backend.services.serviceImplementations;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.Enums.TypeLogement;
import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.dtos.LocataireDetailDTO;
import com.immoGestion.backend.dtos.LocataireLogementAssociationDTO;
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

    @Override
    public void assignLogementToLocataire(LocataireLogementAssociationDTO dto) {
        Locataire locataire = locataireRepository.findById(dto.getLocatairId())
                .orElseThrow(()-> new RuntimeException("Locataire non trouvé") );

        Logement logement = logementRepository.findById(dto.getLogementId())
                .orElseThrow(()-> new RuntimeException("Logement non trouvé"));


        // Vérifier si logement déjà associé à un autre locataire
        Optional<Locataire> locataireExistant = locataireRepository.findByLogement_IdLogement(logement.getIdLogement());
        if (locataireExistant.isPresent() && !locataireExistant.get().getId().equals(locataire.getId())) {
            throw new RuntimeException("Ce logement est déjà associé à un autre locataire !");
        }

        // Vérifier si ce locataire a déjà un logement
        if (locataire.getLogement() != null) {
            throw new RuntimeException("Ce locataire a déjà un logement !");
        }

        locataire.setLogement(logement);
        logement.setStatut(StatutLogement.OCCUPE);//changer la stutu si logement associer


        locataireRepository.save(locataire);
        logementRepository.save(logement);
    }

    @Override
    public LocataireDetailDTO getLocataireDetail(Long locataireId) {
        Locataire locataire = locataireRepository.findById(locataireId)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé"));

        LocataireDetailDTO dto = new LocataireDetailDTO();
        dto.setIdLocataire(locataire.getId());
        dto.setNom(locataire.getNom());
        dto.setPrenom(locataire.getPrenom());
        dto.setCarteIdentite(locataire.getCarteIdentite());
        dto.setEmail(locataire.getEmail());
        dto.setPassword(locataire.getPassword());
        dto.setNumeroTelephone(locataire.getNumeroTelephone());
        dto.setSituationFamiliale(locataire.getSituationFamiliale());

        if (locataire.getLogement() != null) {
            dto.setIdLogement(locataire.getLogement().getIdLogement());
            dto.setNumeroAppartement(locataire.getLogement().getNumeroAppartement());
            dto.setEtageNumber(locataire.getLogement().getEtageNumber());
            dto.setSurface(locataire.getLogement().getSurface());
            dto.setPrix(locataire.getLogement().getPrix());
            dto.setType(locataire.getLogement().getType());
        } else {
            dto.setIdLogement(null); // ⚡ idLogement nullable
            dto.setNumeroAppartement(null);
            dto.setEtageNumber(0);
            dto.setSurface(0.0);
            dto.setPrix(0.0);
            dto.setType(null);
        }
        return dto;
    }

    @Override
    public void libererLogement(Long locataireId, Long logementId) {
        Locataire locataire = locataireRepository.findById(locataireId)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé"));

        if (locataire.getLogement() == null) {
            throw new RuntimeException("Ce locataire n'a pas de logement !");
        }

        Logement logement = locataire.getLogement();
        locataire.setLogement(null);
        locataireRepository.save(locataire);

        // Mettre à jour le statut du logement
        logement.setStatut(StatutLogement.LIBRE);
        logementRepository.save(logement);
    }

    @Override
    public void dissocierLocataire(Long locataireId, Long logementId) {
        Locataire locataire = locataireRepository.findById(locataireId)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé"));

        if (locataire.getLogement() != null && locataire.getLogement().getIdLogement().equals(logementId)) {
            // Dissocier le logement
            locataire.setLogement(null);  // <-- juste nuller la relation
            locataireRepository.save(locataire);

            // Mettre à jour le statut du logement
            Logement logement = logementRepository.findById(logementId)
                    .orElseThrow(() -> new RuntimeException("Logement non trouvé"));
            logement.setStatut(StatutLogement.LIBRE);
            logementRepository.save(logement);
        } else {
            throw new RuntimeException("Le locataire n'est pas associé à ce logement");
        }
    }


}


