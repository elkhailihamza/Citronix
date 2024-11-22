package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.citronix.dto.RecolteDTO;
import org.project.citronix.entity.Recolte;

@Mapper(componentModel = "spring")
public interface RecolteMapper {
    Recolte toRecolte(RecolteDTO recolteDTO);

    @Mapping(target = "recolteDetails", ignore = true)
    @Mapping(target = "ventes", ignore = true)
    RecolteDTO toRecolteDTO(Recolte recolte);
}
