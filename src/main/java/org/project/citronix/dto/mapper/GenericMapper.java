package org.project.citronix.dto.mapper;

import org.project.citronix.dto.FermeDTO;

public interface GenericMapper<Entity, EntityDTO> {
    Entity toEntity(EntityDTO entityDTO);
    EntityDTO toEntityDTO(Entity entity);
}
