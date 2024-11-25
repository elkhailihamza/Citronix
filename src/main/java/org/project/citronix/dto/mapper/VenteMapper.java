package org.project.citronix.dto.mapper;

import org.mapstruct.Mapper;
import org.project.citronix.dto.VenteDTO;
import org.project.citronix.entity.Vente;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    Vente toVente(VenteDTO venteDTO);
    VenteDTO toVenteDTO(Vente vente);
}
