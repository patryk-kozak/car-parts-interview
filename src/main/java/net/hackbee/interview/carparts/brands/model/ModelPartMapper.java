package net.hackbee.interview.carparts.brands.model;

import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelPartMapper {

    ModelPart entityToModelPart(PartEntity entity);

}
