package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.TacheDTO;
import com.immoGestion.backend.models.Tache;
import com.immoGestion.backend.services.serviceInterfaces.TacheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
