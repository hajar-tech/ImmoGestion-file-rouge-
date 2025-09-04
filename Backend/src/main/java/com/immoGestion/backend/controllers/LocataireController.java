package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.dtos.LocataireDetailDTO;
import com.immoGestion.backend.dtos.LocataireLogementAssociationDTO;
import com.immoGestion.backend.services.serviceInterfaces.LocataireService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/locataires")
public class LocataireController {
    private final LocataireService locataireService;

    public LocataireController(LocataireService locataireService) {
        this.locataireService = locataireService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public LocataireDTO creerLocataire(@RequestBody LocataireDTO dto) {
        return locataireService.creerLocataire(dto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public LocataireDTO modifierLocataire(@PathVariable Long id, @RequestBody LocataireDTO dto) {
        return locataireService.modifierLocataire(id, dto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void supprimerLocataire(@PathVariable Long id) {
        locataireService.supprimerLocataire(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<LocataireDTO> getAllLocataires() {
        return locataireService.getAllLocataires();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<LocataireDTO> getLocataire(@PathVariable Long id) {
        return locataireService.getLocataireById(id);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign-logement")
    public ResponseEntity<String> assignLogement(@RequestBody LocataireLogementAssociationDTO dto) {
       try {
           locataireService.assignLogementToLocataire(dto);
           return ResponseEntity.ok("Logement assigné avec succès !");
       }catch (RuntimeException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/details")
    public ResponseEntity<LocataireDetailDTO> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(locataireService.getLocataireDetail(id));
    }




    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/liberer-logement")
    @PermitAll
    public ResponseEntity<String> libererLogement(@RequestBody LocataireLogementAssociationDTO dto) {
        locataireService.libererLogement(dto.getLocatairId(), dto.getLogementId());
        return ResponseEntity.ok("Logement libéré avec succès !");
    }




    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idLocataire}/dissocier/{idLogement}")
    public ResponseEntity<String> dissocierLogement(
            @PathVariable Long idLocataire,
            @PathVariable Long idLogement) {
        locataireService.dissocierLocataire(idLocataire, idLogement);
        return ResponseEntity.ok("Dissociation réussie");
    }

}
