package net.hackbee.interview.carparts;

import net.hackbee.interview.carparts.maintenance.model.Maintenance;
import net.hackbee.interview.carparts.persistence.entity.MaintenanceEntity;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;

import java.time.LocalDate;

public class MaintenanceFixture {
    public static Maintenance septemberTwentyFirst() {
        Maintenance maintenance = new Maintenance();
        maintenance.setBeginDate(LocalDate.of(2020, 9, 20));
        maintenance.setEndDate(LocalDate.of(2020, 9, 21));
        maintenance.setName("Regular Maintenance");
        return maintenance;
    }

    public static MaintenanceEntity entity(PartEntity partEntity, LocalDate date) {
        MaintenanceEntity maintenanceEntity = new MaintenanceEntity();
        maintenanceEntity.setPart(partEntity);
        maintenanceEntity.setEndDate(date);
        maintenanceEntity.setBeginDate(date);
        maintenanceEntity.setName("Maintenance at " + date.toString());
        return maintenanceEntity;
    }

    public static Maintenance ofDate(LocalDate date) {
        Maintenance maintenance = new Maintenance();
        maintenance.setBeginDate(date);
        maintenance.setEndDate(date);
        maintenance.setName("Maintenance at " + date.toString());
        return maintenance;
    }
}
