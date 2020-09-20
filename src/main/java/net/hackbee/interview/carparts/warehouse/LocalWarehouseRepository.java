package net.hackbee.interview.carparts.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface LocalWarehouseRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByPart_Id(Long partId);
}
