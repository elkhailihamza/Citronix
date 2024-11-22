package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.entity.Arbre;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    Arbre toArbre(ArbreDTO arbreDTO);

    @Mapping(target = "recolteDetails", ignore = true)
    @Mapping(target = "champId", source = "champ.id")
    ArbreDTO toArbreDTO(Arbre arbre);
}
