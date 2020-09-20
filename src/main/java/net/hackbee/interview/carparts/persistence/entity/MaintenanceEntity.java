package net.hackbee.interview.carparts.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MAINTENANCES")
public class MaintenanceEntity {

    @Id
    // NOTE: Don't use it in prod. It disables DB batch insert optimization.
    // JPA also need to load it back to context after each insert.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private PartEntity part;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PartEntity getPart() {
        return part;
    }

    public void setPart(PartEntity part) {
        this.part = part;
    }
}
