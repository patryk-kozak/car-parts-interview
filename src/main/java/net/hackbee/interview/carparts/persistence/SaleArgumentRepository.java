package net.hackbee.interview.carparts.persistence;

import net.hackbee.interview.carparts.persistence.entity.SaleArgumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleArgumentRepository extends JpaRepository<SaleArgumentEntity, Long> {

    Integer deleteByPart_Id(Long partId);
}
