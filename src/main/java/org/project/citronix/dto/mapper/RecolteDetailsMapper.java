package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.project.citronix.dto.RecolteDetailsDTO;
import org.project.citronix.entity.RecolteDetails;

@Mapper(componentModel = "spring")
public interface RecolteDetailsMapper {
    RecolteDetails toRecolteDetails(RecolteDetailsDTO recolteDetailsDTO);
    RecolteDetailsDTO toRecolteDetailsDTO(RecolteDetails recolteDetails);
}
