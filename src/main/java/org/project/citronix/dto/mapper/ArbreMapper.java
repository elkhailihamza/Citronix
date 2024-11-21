package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.project.citronix.dto.ArbreDTO;
import org.project.citronix.entity.Arbre;

@Mapper(componentModel = "spring")
public interface ArbreMapper extends GenericMapper<Arbre, ArbreDTO>{
}
