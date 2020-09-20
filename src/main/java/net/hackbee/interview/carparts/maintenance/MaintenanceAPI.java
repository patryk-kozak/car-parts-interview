package net.hackbee.interview.carparts.maintenance;

import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.maintenance.model.Maintenance;
import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceAPI {

    private final MaintenanceService maintenanceService;

    public MaintenanceAPI(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/schedule")
    public List<Maintenance> servicesSchedule(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beginDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return this.maintenanceService.timeline(beginDate, endDate);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/part")
    public PartMaintenance partService(@RequestBody PartMaintenance partServiceDto) throws PartNotFoundException {
        return this.maintenanceService.registerPart(partServiceDto);
    }
}
