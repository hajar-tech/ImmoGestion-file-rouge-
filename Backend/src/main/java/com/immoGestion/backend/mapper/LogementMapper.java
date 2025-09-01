package com.immoGestion.backend.mapper;

import com.immoGestion.backend.dtos.LogementDTO;
import com.immoGestion.backend.models.Logement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogementMapper {

    LogementMapper INSTANCE = Mappers.getMapper(LogementMapper.class);

   //mappe l'entité Logement vers LogementDto
    LogementDTO toDto (Logement logement);


    List<LogementDTO> toDtoList (List<Logement> logements);

    //mappe LogementDto vers l'entité Logement
    Logement toEntity (LogementDTO logementDTO);

    // This method is for updating an existing entity
    @Mapping(target = "idLogement", ignore = true) // idLogement should not be updated
    void updateLogementFromDto(LogementDTO logementDTO, @MappingTarget Logement logement);


}
