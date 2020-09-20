package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import net.hackbee.interview.carparts.persistence.entity.SaleArgumentEntity;

public class SaleArgumentFixture {

    public static SaleArgumentEntity fastDeliveryAndEasyInstall(PartEntity partEntity) {
        SaleArgumentEntity saleArgumentEntity = new SaleArgumentEntity();
        saleArgumentEntity.setPart(partEntity);
        saleArgumentEntity.setDiscountPercentage(0);
        saleArgumentEntity.setEasyInstall(Boolean.TRUE);
        saleArgumentEntity.setFastDelivery(Boolean.TRUE);

        return saleArgumentEntity;
    }

}
