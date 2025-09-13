package com.immoGestion.backend.mapper;

import com.immoGestion.backend.dtos.TacheDTO;
import com.immoGestion.backend.models.Tache;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring")
public interface TacheMapper {

    @Mapping(target = "logement.idLogement", source = "logementId")
    @Mapping(target = "locataire.id", source = "locataireId")
    Tache toEntity(TacheDTO dto);


    @Mapping(source = "logement.idLogement", target = "logementId")
    @Mapping(source = "locataire.id", target = "locataireId")
    TacheDTO toDTO(Tache entity);

}
