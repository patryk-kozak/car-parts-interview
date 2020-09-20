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

import javax.transaction.Transactional;

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

    PartAvailability available(Long id) {
        try {
            PartStock stock = this.warehouseApi.checkStock(id);
            return PartAvailability.available(stock.getAmount(), stock.getShipmentDate());
        } catch (PartNotFoundException e) {
            return PartAvailability.unavailable();
        }
    }

    Part update(Long id, Part partDto) throws PartNotFoundException {
        PartEntity partEntity = this.partRepository.findById(id).orElseThrow(() -> new PartNotFoundException(id));
        partEntity.setName(partDto.getName());
        partEntity.setDescription(partDto.getDescription());
        partEntity.setPrice(partDto.getPrice());
        return partMapper.entityToPart(this.partRepository.save(partEntity));
    }
}
