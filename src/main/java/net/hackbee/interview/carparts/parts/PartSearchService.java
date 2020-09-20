package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartMapper;
import net.hackbee.interview.carparts.persistence.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class PartSearchService {
    
    private final PartRepository partRepository;
    private final PartMapper partMapper;

    PartSearchService(PartRepository partRepository, PartMapper partMapper) {
        this.partRepository = partRepository;
        this.partMapper = partMapper;
    }

    List<Part> findByBrandAndModel(String brand, String model) {
        return partRepository.findByBrandAndModel(brand, model).stream()
                .map(this.partMapper::entityToPart)
                .collect(Collectors.toList());
    }

    List<Part> findByBrandAndModelAndDescription(String brand, String model, String partDescription) {
        return partRepository.findByBrandAndModel(brand, model)
                .stream()
                .filter(p->p.getDescription().contains(partDescription))
                .map(this.partMapper::entityToPart)
                .collect(Collectors.toList());
    }
}
