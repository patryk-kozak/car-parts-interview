package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartAvailability;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PartFixture {

    public static PartEntity diskBrakes() {
        PartEntity entity = new PartEntity();
        entity.setName("Disk brakes");
        entity.setDescription("Awesome brakes");
        entity.setPrice(BigDecimal.valueOf(120L));
        return entity;
    }

    public static PartEntity diskBrakes(ModelEntity model) {
        PartEntity entity = new PartEntity();
        entity.setName("Disk brakes");
        entity.setDescription("Awesome brakes");
        entity.setPrice(BigDecimal.valueOf(120L));
        entity.setModel(model);
        return entity;
    }

    public static PartEntity steeringWheel(ModelEntity model) {
        PartEntity entity = new PartEntity();
        entity.setName("Steering Wheel");
        entity.setDescription("M Performance Steering Wheel");
        entity.setPrice(BigDecimal.valueOf(1200L));
        entity.setModel(model);
        return entity;
    }

    public static Part diskBrakesModel() {
        Part part = new Part();
        part.setName("Disk brakes");
        part.setDescription("Awesome brakes");
        part.setPrice(BigDecimal.valueOf(120L));
        return part;
    }

    public static Part steeringWheelModel() {
        Part entity = new Part();
        entity.setName("Steering Wheel");
        entity.setDescription("M Performance Steering Wheel");
        entity.setPrice(BigDecimal.valueOf(1200L));
        return entity;
    }

    public static Part updatedDiskBrakesModel() {
        Part part = new Part();
        part.setName("Updated disk brakes");
        part.setDescription("Awesome updated brakes");
        part.setPrice(BigDecimal.valueOf(120L));
        return part;
    }

    public static PartAvailability partAvailable() {
        return PartAvailability.available(LocalDate.of(2020, 9, 10));
    }

}
