package com.immoGestion.backend;

import com.immoGestion.backend.Enums.TypeLogement;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.repositories.LogementRepository;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class LogementServiceTest {

    @Autowired
    private LogementService logementService;

    @Autowired
    LogementRepository logementRepository;

    @Test
    void testCreateLogement(){
        Logement logement = new Logement();
        logement.setNumeroAppartement("A101");
        logement.setType(TypeLogement.APPARTEMENT);
        logementRepository.save(logement);

        assertEquals(3 , logementRepository.count());
        assertEquals("A101" , logement.getNumeroAppartement());
    }

    @Test
    void testCreateAndGetAll() {
        Logement logement1 = new Logement();
        logement1.setType(TypeLogement.APPARTEMENT);


        Logement logement2 = new Logement();
        logement2.setType(TypeLogement.DUPLEX);


        logementRepository.save(logement1);
        logementRepository.save(logement2);

        List<Logement> all = logementRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testUpdate() {
        Logement logement = new Logement();
        logement.setType(TypeLogement.DUPLEX);
        logement.setNumeroAppartement("A101");
        logement = logementRepository.save(logement);

        logement.setNumeroAppartement("A202");
        logementRepository.save(logement);

        Logement updated = logementRepository.findById(logement.getIdLogement()).orElseThrow();
        assertEquals("A202", updated.getNumeroAppartement());
    }


    @Test
    void testDelete() {
        Logement logement = new Logement();
        logement.setType(TypeLogement.APPARTEMENT);
        logement = logementRepository.save(logement);

        logementRepository.deleteById(logement.getIdLogement());
        assertTrue(logementRepository.findById(logement.getIdLogement()).isEmpty());
    }
}
