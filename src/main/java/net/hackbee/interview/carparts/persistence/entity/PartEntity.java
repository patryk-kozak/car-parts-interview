package net.hackbee.interview.carparts.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PARTS")
public class PartEntity {

    @Id
    // NOTE: Don't use it in prod. It disables DB batch insert optimization.
    // JPA also need to load it back to context after each insert.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "part")
    private SaleArgumentEntity saleArgument;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "model_id")
    private ModelEntity model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
    private Set<MaintenanceEntity> maintenances = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SaleArgumentEntity getSaleArgument() {
        return saleArgument;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

}
