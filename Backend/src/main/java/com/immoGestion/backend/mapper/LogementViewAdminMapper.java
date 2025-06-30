package com.immoGestion.backend.mapper;

import com.immoGestion.backend.dtos.LogementViewAdmin;
import com.immoGestion.backend.models.Logement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface LogementViewAdminMapper {

    @Mapping(source = "numeroAppartement", target = "numeroLogement")
    @Mapping(source = "etageNumber", target = "numeroEtage")
    @Mapping(source = "type", target = "typeLogement")
    @Mapping(source = "statut", target = "statutLogement")
    @Mapping(source = "locataire.nom", target = "nomLocataire")
    @Mapping(source = "locataire.numeroTelephone", target = "contactLocataire")
    @Mapping(expression = "java(logement.getPaiements() != null ? logement.getPaiements().size() : 0)", target = "paiementNombre")
    @Mapping(expression = "java(logement.getCharges() != null ? logement.getCharges().size() : 0)", target = "chargeNombre")
    LogementViewAdmin toDto(Logement logement);

    List<LogementViewAdmin> ToDto (List<Logement> logements);
}
