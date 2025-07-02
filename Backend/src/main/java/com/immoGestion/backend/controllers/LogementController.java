package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("logement")
public class LogementController {

    private final LogementService logementService;

    public LogementController(LogementService logementService){
        this.logementService = logementService;
    }

    @PostMapping("/creerLogement")
    public String creerLogement(@RequestBody Logement logement){
       return logementService.creerLogement(logement);
    }

    @GetMapping("/getAllToAdmin")
    public ResponseEntity<List<LogementViewAdmin>> afficherLogementsAdmin(){
        List<LogementViewAdmin> logementViewAdmins = logementService.afficherToutLogements();
       return ResponseEntity.ok(logementViewAdmins);
    }
}
