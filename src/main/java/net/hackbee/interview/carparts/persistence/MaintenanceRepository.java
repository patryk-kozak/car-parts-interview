package net.hackbee.interview.carparts.persistence;

import net.hackbee.interview.carparts.persistence.entity.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    List<MaintenanceEntity> findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate beginDate,
                                                                                      LocalDate endDate);

}
