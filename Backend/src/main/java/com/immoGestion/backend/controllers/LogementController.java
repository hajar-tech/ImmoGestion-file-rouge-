package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.LogementDTO;
import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logements")
public class LogementController {

    private final LogementService logementService;

    public LogementController(LogementService logementService){
        this.logementService = logementService;
    }

    @PostMapping("/creerLogement")
    public ResponseEntity<LogementDTO>  creerLogement(@RequestBody LogementDTO logementDto){
       LogementDTO createdLogement = logementService.creerLogement(logementDto);
       return new  ResponseEntity<>(createdLogement,HttpStatus.CREATED);
    }

    @GetMapping("/getAllToAdmin")
    public ResponseEntity<List<LogementViewAdmin>> afficherLogementsAdmin(){
        List<LogementViewAdmin> logementViewAdmins = logementService.afficherToutLogements();
       return ResponseEntity.ok(logementViewAdmins);
    }
}
