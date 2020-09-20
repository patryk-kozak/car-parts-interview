package net.hackbee.interview.carparts.maintenance.model;

import net.hackbee.interview.carparts.persistence.entity.MaintenanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
    
    Maintenance entityToMaintenance(MaintenanceEntity entity);
    
    @Mapping(target = "partId", source = "part.id")
    PartMaintenance entityToPartMaintenance(MaintenanceEntity entity);
}
