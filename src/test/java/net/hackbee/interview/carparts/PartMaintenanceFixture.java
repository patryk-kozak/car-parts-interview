package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;

import java.time.LocalDate;

public class PartMaintenanceFixture {

    public static PartMaintenance clutchReplace() {
        PartMaintenance partMaintenance = new PartMaintenance();
        partMaintenance.setName("Clutch replace");
        partMaintenance.setPartId(1L);
        partMaintenance.setBeginDate(LocalDate.of(2020, 2, 2));
        partMaintenance.setEndDate(LocalDate.of(2020, 2, 5));
        return partMaintenance;
    }

    public static PartMaintenance clutchReplace(Long partId) {
        PartMaintenance partMaintenance = new PartMaintenance();
        partMaintenance.setName("Clutch replace");
        partMaintenance.setPartId(partId);
        partMaintenance.setBeginDate(LocalDate.of(2020, 2, 2));
        partMaintenance.setEndDate(LocalDate.of(2020, 2, 5));
        return partMaintenance;
    }

}
