package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.entity.Ferme;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    Ferme toFerme(FermeDTO fermeDTO);

    @Mapping(target = "champs", ignore = true)
    FermeDTO toFermeDTO(Ferme ferme);
}
