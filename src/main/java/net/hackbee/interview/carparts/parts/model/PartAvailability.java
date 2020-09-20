package net.hackbee.interview.carparts.parts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class PartAvailability {

    private final Boolean available;
    private final LocalDate shipmentDate;

    private PartAvailability(Boolean available, LocalDate shipmentDate) {
        this.available = available;
        this.shipmentDate = shipmentDate;
    }

    public static PartAvailability available(LocalDate shipmentDate) {
        return new PartAvailability(Boolean.TRUE, shipmentDate);
    }

    public static PartAvailability unavailable() {
        return new PartAvailability(Boolean.FALSE, null);
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public LocalDate getShipmentDate() {
        return this.shipmentDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("available", available)
                .append("shipmentDate", shipmentDate)
                .toString();
    }
}
