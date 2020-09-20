package net.hackbee.interview.carparts.warehouse;

import net.hackbee.interview.carparts.PartNotFoundException;
import org.springframework.stereotype.Component;

@Component
class LocalWarehouseAPI implements WarehouseAPI {

    private final LocalWarehouseRepository localWarehouseRepository;

    public LocalWarehouseAPI(LocalWarehouseRepository localWarehouseRepository) {
        this.localWarehouseRepository = localWarehouseRepository;
    }

    @Override
    public PartStock checkStock(Long partId) throws PartNotFoundException {
        StockEntity stock =
                localWarehouseRepository.findByPart_Id(partId).orElseThrow(() -> new PartNotFoundException(partId));
        return new PartStock(stock.getPart().getId(), stock.getAmount(), stock.getShipmentDate());
    }
}
