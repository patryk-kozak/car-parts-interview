package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartAvailability;
import net.hackbee.interview.carparts.parts.model.PartMapperImpl;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.warehouse.PartStock;
import net.hackbee.interview.carparts.warehouse.WarehouseAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    @Mock
    private PartRepository partRepositoryMock;
    @Mock
    private WarehouseAPI warehouseApiMock;
    @Mock
    private PartMapperImpl partMapperMock;

    @InjectMocks
    private PartService subject;

    @Test
    void partAvailable() throws PartNotFoundException {
        given(warehouseApiMock.checkStock(1L))
                .willReturn(new PartStock(1L, 10, LocalDate.now()));

        PartAvailability partAvailability = subject.available(1L);

        assertThat(partAvailability.getAvailable()).isTrue();
        assertThat(partAvailability.getShipmentDate()).isToday();
    }

    @Test
    void partUnavailable() throws PartNotFoundException {
        given(warehouseApiMock.checkStock(1L))
                .willReturn(new PartStock(1L, 0, LocalDate.now()));

        PartAvailability partAvailability = subject.available(1L);

        assertThat(partAvailability.getAvailable()).isFalse();
        assertThat(partAvailability.getShipmentDate()).isNull();
    }

    @Test
    void partUnavailableException() throws PartNotFoundException {
        given(warehouseApiMock.checkStock(1L)).willThrow(new PartNotFoundException(1L));

        PartAvailability partAvailability = subject.available(1L);

        assertThat(partAvailability.getAvailable()).isFalse();
        assertThat(partAvailability.getShipmentDate()).isNull();
    }

    @Test
    void update() throws PartNotFoundException {
        given(partRepositoryMock.findById(1L))
                .willReturn(Optional.of(PartFixture.diskBrakes()));
        given(partRepositoryMock.save(any())).willReturn(PartFixture.diskBrakes());
        given(partMapperMock.entityToPart(any())).willCallRealMethod();

        Part updated = subject.update(1L, PartFixture.diskBrakesModel());

        assertThat(updated).isEqualToComparingFieldByField(PartFixture.diskBrakesModel());
    }

    @Test
    void partNotFoundForUpdate() {
        given(partRepositoryMock.findById(999L))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> subject.update(999L, PartFixture.diskBrakesModel()))
                .isInstanceOf(PartNotFoundException.class)
                .hasMessageContaining("Part with id = 999 has not been found.");
    }
}