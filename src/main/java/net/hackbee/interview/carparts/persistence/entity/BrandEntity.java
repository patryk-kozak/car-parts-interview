package net.hackbee.interview.carparts.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRANDS")
public class BrandEntity {

    @Id
    // NOTE: Don't use it in prod. It disables DB batch insert optimization.
    // JPA also need to load it back to context after each insert.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<ModelEntity> models = new HashSet<>();

    public Set<ModelEntity> getModels() {
        return models;
    }

    public void setModels(Set<ModelEntity> models) {
        this.models = models;
    }

    public void setName(String name) {
        this.name = name;
    }
}
