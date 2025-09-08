package com.immoGestion.backend;

import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.dtos.LocataireLogementAssociationDTO;
import com.immoGestion.backend.mapper.LocataireMapper;
import com.immoGestion.backend.models.Locataire;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.repositories.LocataireRepository;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.services.serviceImplementations.LocataireImpl;
import com.immoGestion.backend.services.serviceInterfaces.LocataireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocataireServiceTest {

    @Mock
    private LocataireRepository locataireRepository;
    @Mock
    private LogementRepository logementRepository;

    @Mock
    private LocataireMapper locataireMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LocataireImpl locataireService;

    @Test
    void testCreerLocataire_Success() {
        // 🔹 Arrange
        LocataireDTO dto = new LocataireDTO();
        dto.setNom("Ali");

        Locataire entity = new Locataire();
        entity.setNom("Ali");

        // mapping DTO -> Entity
        Mockito.when(locataireMapper.toEntity(dto)).thenReturn(entity);

        // repository.save(entity) -> return entity
        Mockito.when(locataireRepository.save(entity)).thenReturn(entity);

        // mapping Entity -> DTO
        Mockito.when(locataireMapper.toDto(entity)).thenReturn(dto);

        // 🔹 Act
        LocataireDTO result = locataireService.creerLocataire(dto);

        // 🔹 Assert
        assertNotNull(result);
        assertEquals("Ali", result.getNom());

        // vérifier que repository.save a été appelé
        Mockito.verify(locataireRepository).save(entity);
    }


    @Test
    void testAssignLogementToLocataire_Success() {
        // Arrange
        LocataireLogementAssociationDTO dto = new LocataireLogementAssociationDTO();
        dto.setLocatairId(1L);
        dto.setLogementId(10L);

        Locataire locataire = new Locataire();
        locataire.setId(1L);

        Logement logement = new Logement();
        logement.setIdLogement(10L);
        logement.setStatut(StatutLogement.LIBRE);

        // mocks
        Mockito.when(locataireRepository.findById(1L)).thenReturn(Optional.of(locataire));
        Mockito.when(logementRepository.findById(10L)).thenReturn(Optional.of(logement));
        Mockito.when(locataireRepository.findByLogement_IdLogement(10L)).thenReturn(Optional.empty());

        // Act
        locataireService.assignLogementToLocataire(dto);

        // Assert
        assertEquals(logement, locataire.getLogement());
        assertEquals(StatutLogement.OCCUPE, logement.getStatut());

        Mockito.verify(locataireRepository).save(locataire);
        Mockito.verify(logementRepository).save(logement);
    }



    @Test
    void testAssignLogementToLocataire_LogementDejaAssocie() {
        // Arrange
        LocataireLogementAssociationDTO dto = new LocataireLogementAssociationDTO();
        dto.setLocatairId(1L);
        dto.setLogementId(10L);

        Locataire locataire = new Locataire();
        locataire.setId(1L);

        Locataire autreLocataire = new Locataire();
        autreLocataire.setId(2L);

        Logement logement = new Logement();
        logement.setIdLogement(10L);

        Mockito.when(locataireRepository.findById(1L)).thenReturn(Optional.of(locataire));
        Mockito.when(logementRepository.findById(10L)).thenReturn(Optional.of(logement));
        Mockito.when(locataireRepository.findByLogement_IdLogement(10L))
                .thenReturn(Optional.of(autreLocataire));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> locataireService.assignLogementToLocataire(dto));

        assertEquals("Ce logement est déjà associé à un autre locataire !", ex.getMessage());
    }

    @Test
    void testAssignLogementToLocataire_LocataireDejaALogement() {
        // Arrange
        LocataireLogementAssociationDTO dto = new LocataireLogementAssociationDTO();
        dto.setLocatairId(1L);
        dto.setLogementId(10L);

        Logement logement1 = new Logement();
        logement1.setIdLogement(5L);

        Locataire locataire = new Locataire();
        locataire.setId(1L);
        locataire.setLogement(logement1); // déjà un logement

        Logement logement2 = new Logement();
        logement2.setIdLogement(10L);

        Mockito.when(locataireRepository.findById(1L)).thenReturn(Optional.of(locataire));
        Mockito.when(logementRepository.findById(10L)).thenReturn(Optional.of(logement2));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> locataireService.assignLogementToLocataire(dto));

        assertEquals("Ce locataire a déjà un logement !", ex.getMessage());
    }

}
