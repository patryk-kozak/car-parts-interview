package net.hackbee.interview.carparts.maintenance;

import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.maintenance.model.Maintenance;
import net.hackbee.interview.carparts.maintenance.model.MaintenanceMapper;
import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;
import net.hackbee.interview.carparts.persistence.MaintenanceRepository;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.persistence.entity.MaintenanceEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final PartRepository partRepository;

    MaintenanceService(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, PartRepository partRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.partRepository = partRepository;
    }

    public List<Maintenance> timeline(LocalDate beginDate, LocalDate endDate) {
        return maintenanceRepository
                .findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(beginDate, endDate)
                .stream()
                .map(this.maintenanceMapper::entityToMaintenance)
                .collect(Collectors.toList());
    }

    public PartMaintenance registerPart(PartMaintenance partServiceDto) throws PartNotFoundException {
        MaintenanceEntity maintenance = new MaintenanceEntity();
        maintenance.setName(partServiceDto.getName());
        maintenance.setBeginDate(partServiceDto.getBeginDate());
        maintenance.setEndDate(partServiceDto.getEndDate());
        maintenance.setPart(partRepository.findById(partServiceDto.getPartId())
                .orElseThrow(() -> new PartNotFoundException(partServiceDto.getPartId())));

        return maintenanceMapper.entityToPartMaintenance(
                maintenanceRepository.save(maintenance)
        );
    }
}
