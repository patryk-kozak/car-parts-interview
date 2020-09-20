package net.hackbee.interview.carparts.warehouse;

import java.time.LocalDate;

public class PartStock {

    private final Long partId;
    private final Integer amount;
    private final LocalDate shipmentDate;

    public PartStock(Long partId, Integer amount, LocalDate shipmentDate) {
        this.partId = partId;
        this.amount = amount;
        this.shipmentDate = shipmentDate;
    }

    public Long getPartId() {
        return partId;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }
}
