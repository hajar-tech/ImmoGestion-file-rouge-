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

    LogementDTO toDto (Logement logement);
    List<LogementDTO> toDtoList (List<Logement> logements);
    Logement toEntity (LogementDTO logementDTO);

    // This method is for updating an existing entity
    @Mapping(target = "idLogement", ignore = true) // idLogement should not be updated
    void updateLogementFromDto(LogementDTO logementDTO, @MappingTarget Logement logement);


}
