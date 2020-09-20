package net.hackbee.interview.carparts.persistence;

import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Custom query to speed up, default findByBrand_name is kinda slow.
 */
public interface PartRepository extends JpaRepository<PartEntity, Long> {

    @Query(
            "select p from PartEntity p " +
                    "where p.model.brand.name = :brand " +
                    "and p.model.name = :model"
    )
    List<PartEntity> findByBrandAndModel(String brand, String model);

    @Query(
            "select p from PartEntity p " +
                    "where p.model.name = :model"
    )
    List<PartEntity> findByModel(String model);
}
