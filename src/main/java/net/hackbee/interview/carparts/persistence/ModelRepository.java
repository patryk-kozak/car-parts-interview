package net.hackbee.interview.carparts.persistence;

import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    List<ModelEntity> findByBrand_name(String brand);
}
