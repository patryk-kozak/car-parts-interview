package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartAvailability;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartsAPI {

    private final PartSearchService partsSearchService;
    private final PartService partService;

    public PartsAPI(PartSearchService partsSearchService, PartService partService) {
        this.partsSearchService = partsSearchService;
        this.partService = partService;
    }
    
    @PutMapping("{id}")
    public Part modify(@PathVariable Long id, @RequestBody Part partDto) throws PartNotFoundException {
        return this.partService.update(id, partDto);
    }

    @GetMapping("{id}/availability")
    public PartAvailability partAvailability(@PathVariable Long id) {
        return this.partService.available(id);
    }

    @GetMapping("/{brand}/{model}")
    public List<Part> partsByBrandAndModel(@PathVariable String brand, @PathVariable String model,
                                           @RequestParam(required = false) String partDescription) {
        if (partDescription != null) {
            return this.partsSearchService.findByBrandAndModelAndDescription(brand, model, partDescription);
        }
        return this.partsSearchService.findByBrandAndModel(brand, model);
    }

}
