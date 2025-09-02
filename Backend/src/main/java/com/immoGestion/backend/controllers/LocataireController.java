package com.immoGestion.backend.controllers;

import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.dtos.LocataireDetailDTO;
import com.immoGestion.backend.dtos.LocataireLogementAssociationDTO;
import com.immoGestion.backend.services.serviceInterfaces.LocataireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/locataires")
public class LocataireController {
    private final LocataireService locataireService;

    public LocataireController(LocataireService locataireService) {
        this.locataireService = locataireService;
    }

    @PostMapping
    public LocataireDTO creerLocataire(@RequestBody LocataireDTO dto) {
        return locataireService.creerLocataire(dto);
    }

    @PutMapping("/{id}")
    public LocataireDTO modifierLocataire(@PathVariable Long id, @RequestBody LocataireDTO dto) {
        return locataireService.modifierLocataire(id, dto);
    }

    @DeleteMapping("/{id}")
    public void supprimerLocataire(@PathVariable Long id) {
        locataireService.supprimerLocataire(id);
    }

    @GetMapping
    public List<LocataireDTO> getAllLocataires() {
        return locataireService.getAllLocataires();
    }

    @GetMapping("/{id}")
    public Optional<LocataireDTO> getLocataire(@PathVariable Long id) {
        return locataireService.getLocataireById(id);
    }


    @PostMapping("/assign-logement")
    public ResponseEntity<String> assignLogement(@RequestBody LocataireLogementAssociationDTO dto) {
        locataireService.assignLogementToLocataire(dto);
        return ResponseEntity.ok("Logement assigné avec succès !");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<LocataireDetailDTO> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(locataireService.getLocataireDetail(id));
    }

}
