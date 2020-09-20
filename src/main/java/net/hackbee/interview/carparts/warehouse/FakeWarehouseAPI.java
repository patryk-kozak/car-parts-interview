package net.hackbee.interview.carparts.warehouse;

import net.hackbee.interview.carparts.PartNotFoundException;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Profile("test")
public class FakeWarehouseAPI implements WarehouseAPI {
    
    @Override
    public PartStock checkStock(Long partId) throws PartNotFoundException {
        return new PartStock(1L, 100, LocalDate.of(2020, 9, 10));
    }
}
