package net.hackbee.interview.carparts.warehouse;

import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

/**
 * Use a fake pattern to easily stub all "external" dependencies.
 * Usually automated by CDC for REST, but fake classes are good too.
 */
@Profile("test")
public class FakeWarehouseAPI implements WarehouseAPI {

    @Override
    public PartStock checkStock(Long partId) {
        return new PartStock(1L, 100, LocalDate.of(2020, 9, 10));
    }
}
