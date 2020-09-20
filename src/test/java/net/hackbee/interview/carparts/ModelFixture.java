package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.brands.model.Model;
import net.hackbee.interview.carparts.brands.model.ModelPart;
import net.hackbee.interview.carparts.persistence.entity.BrandEntity;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class ModelFixture {

    public static Model m8() {
        Model m8 = new Model("M8");
        ModelPart modelPart = new ModelPart();
        modelPart.setName("Disk brakes");
        modelPart.setDescription("Awesome brakes");
        modelPart.setPrice(BigDecimal.valueOf(120));
        m8.setParts(Collections.singletonList(
                modelPart
        ));
        return m8;
    }

    public static ModelEntity m8Entity(BrandEntity brandEntity, PartEntity partEntity) {
        ModelEntity m8 = new ModelEntity();
        m8.setName("M8");
        m8.setProducedFrom(LocalDate.of(2019, 10, 10));
        m8.setProducedSince(LocalDate.of(2018, 2, 12));
        m8.setBrand(brandEntity);
        m8.setParts(Collections.singleton(partEntity));
        return m8;
    }
    
    public static ModelEntity partlessM8Entity(BrandEntity brandEntity) {
        ModelEntity m8 = new ModelEntity();
        m8.setName("M8");
        m8.setProducedFrom(LocalDate.of(2019, 10, 10));
        m8.setProducedSince(LocalDate.of(2018, 2, 12));
        m8.setBrand(brandEntity);
        return m8;
    }
}
