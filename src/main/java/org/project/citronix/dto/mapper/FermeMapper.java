package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.project.citronix.dto.FermeDTO;
import org.project.citronix.entity.Ferme;

@Mapper(componentModel = "spring")
public interface FermeMapper extends GenericMapper<Ferme, FermeDTO> {

}
