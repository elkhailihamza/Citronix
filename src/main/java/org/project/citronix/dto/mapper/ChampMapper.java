package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.project.citronix.dto.ChampDTO;
import org.project.citronix.entity.Champ;

@Mapper(componentModel = "spring")
public interface ChampMapper extends GenericMapper<Champ, ChampDTO> {
}