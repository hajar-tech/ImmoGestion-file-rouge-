package com.immoGestion.backend.mapper;

import com.immoGestion.backend.dtos.LocataireDTO;
import com.immoGestion.backend.models.Locataire;
import com.immoGestion.backend.models.Logement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocataireMapper {


    LocataireDTO toDto(Locataire locataire);

    Locataire toEntity(LocataireDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLocataireFromDto(LocataireDTO dto, @MappingTarget Locataire entity);


}

