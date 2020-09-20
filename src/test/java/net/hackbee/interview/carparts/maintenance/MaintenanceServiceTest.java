package net.hackbee.interview.carparts.maintenance;

import net.hackbee.interview.carparts.MaintenanceFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.PartMaintenanceFixture;
import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.maintenance.model.Maintenance;
import net.hackbee.interview.carparts.maintenance.model.MaintenanceMapperImpl;
import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;
import net.hackbee.interview.carparts.persistence.MaintenanceRepository;
import net.hackbee.interview.carparts.persistence.PartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

    @Mock
    private MaintenanceRepository maintenanceRepositoryMock;
    @Mock
    private MaintenanceMapperImpl maintenanceMapperMock;
    @Mock
    private PartRepository partRepositoryMock;

    @InjectMocks
    private MaintenanceService subject;

    @Test
    void timeline() {
        LocalDate beginDate = LocalDate.of(2020, 9, 10);
        LocalDate endDate = LocalDate.of(2020, 9, 12);
        given(maintenanceRepositoryMock.findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(beginDate, endDate))
                .willReturn(Collections.singletonList(MaintenanceFixture.entity(PartFixture.diskBrakes(),
                        beginDate.plusDays(1))));
        given(maintenanceMapperMock.entityToMaintenance(any())).willCallRealMethod();

        List<Maintenance> maintenances = subject.timeline(beginDate, endDate);

        assertThat(maintenances).usingFieldByFieldElementComparator()
                .isEqualTo(Collections.singletonList(MaintenanceFixture.ofDate(beginDate.plusDays(1))));
    }

    @Test
    void registerPart() throws PartNotFoundException {
        given(partRepositoryMock.findById(any())).willReturn(
                Optional.of(PartFixture.diskBrakes())
        );
        given(maintenanceMapperMock.entityToPartMaintenance(any())).willReturn(PartMaintenanceFixture.clutchReplace());

        PartMaintenance maintenance = subject.registerPart(PartMaintenanceFixture.clutchReplace());

        assertThat(maintenance).isEqualToComparingFieldByField(PartMaintenanceFixture.clutchReplace());
    }

    @Test
    void registerPartException() {
        assertThatThrownBy(() ->
                subject.registerPart(PartMaintenanceFixture.clutchReplace())
        ).isInstanceOf(PartNotFoundException.class)
                .hasMessageContaining("Part with id = 1 has not been found.");
    }
}