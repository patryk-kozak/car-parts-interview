package net.hackbee.interview.carparts.parts.model;

import java.time.LocalDate;

public class PartAvailability {

    private final Boolean available;
    private final LocalDate shipmentDate;

    private PartAvailability(boolean available, LocalDate shipmentDate) {
        this.available = available;
        this.shipmentDate = shipmentDate;
    }

    public static PartAvailability available(int amount, LocalDate shipmentDate) {
        return new PartAvailability(amount > 0, shipmentDate);
    }


    public static PartAvailability unavailable() {
        return new PartAvailability(false, null);
    }


    public Boolean getAvailable() {
        return available;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }
}
