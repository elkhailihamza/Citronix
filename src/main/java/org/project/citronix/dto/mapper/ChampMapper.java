package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.entity.Champ;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    Champ toChamp(ChampDTO champDTO);

    @Mapping(target = "arbres", ignore = true)
    @Mapping(target = "fermeId", source = "ferme.id")
    ChampDTO toChampDTO(Champ champ);
}
