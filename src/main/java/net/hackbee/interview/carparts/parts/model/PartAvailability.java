package net.hackbee.interview.carparts.parts.model;

import java.time.LocalDate;

public class PartAvailability {

    private final Boolean available;
    private final LocalDate shipmentDate;

    private PartAvailability(int amount, LocalDate shipmentDate) {
        this.available = amount > 0;
        this.shipmentDate = shipmentDate;
    }

    public static PartAvailability available(int amount, LocalDate shipmentDate) {
        return new PartAvailability(amount, shipmentDate);
    }


    public static PartAvailability unavailable() {
        return new PartAvailability(0, null);
    }


    public Boolean getAvailable() {
        return available;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }
}
