package org.project.citronix.dto.mapper;

public interface GenericMapper<Entity, EntityDTO> {
    Entity toEntity(EntityDTO entityDTO);
    EntityDTO toEntityDTO(Entity entity);
}
