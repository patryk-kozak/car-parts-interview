package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.brands.model.BrandProfile;
import net.hackbee.interview.carparts.persistence.entity.BrandEntity;

import java.util.Collections;

import static net.hackbee.interview.carparts.ModelFixture.m8;

public class BrandFixture {

    public static BrandProfile profileBMW() {
        BrandProfile brandProfile = new BrandProfile("BMW");
        brandProfile.setModels(Collections.singletonList(m8()));
        return brandProfile;
    }

    public static BrandEntity entityBMW() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("BMW");
        return brandEntity;
    }

}
