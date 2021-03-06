package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartAvailability;
import net.hackbee.interview.carparts.parts.model.PartMapper;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import net.hackbee.interview.carparts.warehouse.PartStock;
import net.hackbee.interview.carparts.warehouse.WarehouseAPI;
import org.springframework.stereotype.Service;

@Service
class PartService {

    private final PartRepository partRepository;
    private final WarehouseAPI warehouseApi;
    private final PartMapper partMapper;

    PartService(PartRepository partRepository, WarehouseAPI warehouseApi, PartMapper partMapper) {
        this.partRepository = partRepository;
        this.warehouseApi = warehouseApi;
        this.partMapper = partMapper;
    }

    public PartAvailability available(Long id) {
        try {
            PartStock stock = this.warehouseApi.checkStock(id);
            if (stock.getAmount() > 0) {
                return PartAvailability.available(stock.getShipmentDate());
            } else {
                return PartAvailability.unavailable();
            }
        } catch (PartNotFoundException e) {
            return PartAvailability.unavailable();
        }
    }

    public Part update(Long id, Part partDto) throws PartNotFoundException {
        PartEntity partEntity = this.partRepository.findById(id).orElseThrow(() -> new PartNotFoundException(id));
        partEntity.setName(partDto.getName());
        partEntity.setDescription(partDto.getDescription());
        partEntity.setPrice(partDto.getPrice());
        return partMapper.entityToPart(this.partRepository.save(partEntity));
    }
}
