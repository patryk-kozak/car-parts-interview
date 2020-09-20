package net.hackbee.interview.carparts.parts.model;

import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartMapper {
    
    Part entityToPart(PartEntity entity);
    
}
