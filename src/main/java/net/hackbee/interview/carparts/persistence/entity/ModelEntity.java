package net.hackbee.interview.carparts.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MODELS")
public class ModelEntity {

    @Id
    // NOTE: Don't use it in prod. It disables DB batch insert optimization.
    // JPA also need to load it back to context after each insert.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate producedFrom;

    private LocalDate producedSince;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    private Set<PartEntity> parts = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProducedFrom() {
        return producedFrom;
    }

    public void setProducedFrom(LocalDate producedFrom) {
        this.producedFrom = producedFrom;
    }

    public LocalDate getProducedSince() {
        return producedSince;
    }

    public void setProducedSince(LocalDate producedSince) {
        this.producedSince = producedSince;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public Set<PartEntity> getParts() {
        return parts;
    }

    public void setParts(Set<PartEntity> parts) {
        this.parts = parts;
    }
}
