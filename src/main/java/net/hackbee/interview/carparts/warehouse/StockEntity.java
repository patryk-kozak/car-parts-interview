package net.hackbee.interview.carparts.warehouse;

import net.hackbee.interview.carparts.persistence.entity.PartEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STOCKS")
class StockEntity {

    private final int amount = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // NOTE: Removed PartEntity reference here.
    // That way packages don't depend on each other and can easily scale.
    @ManyToOne
    @JoinColumn(name = "part_id")
    private PartEntity part;
    private LocalDate shipmentDate;

    public int getAmount() {
        return amount;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public PartEntity getPart() {
        return part;
    }
}
