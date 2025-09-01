package com.immoGestion.backend.controllers;

import com.immoGestion.backend.Enums.ProprieteLogement;
import com.immoGestion.backend.Enums.StatutLogement;
import com.immoGestion.backend.dtos.LogementDTO;
import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import com.immoGestion.backend.services.serviceInterfaces.LogementService;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/getAll")
    public ResponseEntity<List<LogementDTO>> afficherLogements(){
        List<LogementDTO> logements = logementService.getAllLogements();
       return new  ResponseEntity<>(logements, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LogementDTO> afficherLogementById(@PathVariable Long id){
       return logementService.getLogementById(id)
               .map(logementDTO -> new ResponseEntity<>(logementDTO, HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogementDTO> modifierLogement (@PathVariable Long id , @RequestBody LogementDTO logementDTO){
        try{
            LogementDTO updatedLogement = logementService.updateLogement(id , logementDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> supprimerLogement (@PathVariable Long id ){
        try {
            logementService.deleteLogement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/status/{statut}")
    public ResponseEntity<List<LogementDTO>> afficherLogementsByStatus(StatutLogement statut){
        List<LogementDTO> logementDTOS = logementService.getLogementsByStatus(statut);
        if(logementDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logementDTOS , HttpStatus.OK);
    }


    @GetMapping("/filterPropriete")
    public ResponseEntity<List<Logement>> filterLogementsByProperties(
            @RequestParam String property,
            @RequestParam String value) {

        // Validation simple
        if (!"propriete".equals(property)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ProprieteLogement proprieteEnum = ProprieteLogement.valueOf(value); // Convert string -> enum
            List<Logement> logements = logementService.getLogementsByPropriete(proprieteEnum);
            return ResponseEntity.ok(logements);
        } catch (IllegalArgumentException e) {
            // Si value ma-matcha ma3a enum
            return ResponseEntity.badRequest().build();
        }
    }



}
