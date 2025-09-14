package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.TacheAffichageDTO;
import com.immoGestion.backend.dtos.TacheDTO;
import com.immoGestion.backend.services.serviceInterfaces.TacheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService){
        this.tacheService = tacheService;
    }

    @PostMapping
    public ResponseEntity<TacheDTO> createTache(@RequestBody TacheDTO dto){
        TacheDTO saved = tacheService.createTache(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/locataire/{locataireId}/incidents")
    public List<TacheDTO> getIncidentsByLocataire(@PathVariable Long locataireId){
        return tacheService.getIncidentsByLocataire(locataireId);
    }

    @GetMapping("/getAll")
    public List<TacheAffichageDTO> getAllTaches (){
        return tacheService.getAllTaches();
    }

    @GetMapping("/total")
    public int getTotalTache(){
        return tacheService.getTotalTache();
    }

}
