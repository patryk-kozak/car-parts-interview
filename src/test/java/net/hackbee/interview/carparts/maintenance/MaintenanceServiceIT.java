package net.hackbee.interview.carparts.maintenance;

import net.hackbee.interview.carparts.MaintenanceFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.PartMaintenanceFixture;
import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.maintenance.model.Maintenance;
import net.hackbee.interview.carparts.maintenance.model.MaintenanceMapperImpl;
import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;
import net.hackbee.interview.carparts.persistence.MaintenanceRepository;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import({MaintenanceService.class, MaintenanceMapperImpl.class})
@ActiveProfiles("test")
public class MaintenanceServiceIT {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MaintenanceService subject;

    @Test
    public void shouldReturnMaintenancesBetweenDates() {
        // given
        LocalDate beginDate = LocalDate.of(2020, 9, 10);
        LocalDate endDate = LocalDate.of(2020, 9, 12);
        PartEntity partEntity = testEntityManager.persist(PartFixture.diskBrakes());
        testEntityManager.persist(MaintenanceFixture.entity(partEntity, beginDate.minusDays(5)));
        testEntityManager.persist(MaintenanceFixture.entity(partEntity, beginDate));
        testEntityManager.persist(MaintenanceFixture.entity(partEntity, beginDate.plusDays(1)));
        testEntityManager.persist(MaintenanceFixture.entity(partEntity, endDate));
        testEntityManager.persist(MaintenanceFixture.entity(partEntity, endDate.plusDays(5)));

        // when
        List<Maintenance> maintenances = subject.timeline(beginDate, endDate);

        // then
        assertThat(maintenances.size()).isEqualTo(3);
        assertThat(maintenances).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
                MaintenanceFixture.ofDate(beginDate),
                MaintenanceFixture.ofDate(beginDate.plusDays(1)),
                MaintenanceFixture.ofDate(endDate)
        );
    }

    @Test
    public void shouldRegisterNewMaintenanceForPart() throws PartNotFoundException {
        // given
        PartEntity partEntity = testEntityManager.persist(PartFixture.diskBrakes());

        // when
        PartMaintenance partMaintenance = subject.registerPart(PartMaintenanceFixture.clutchReplace(partEntity.getId()));

        // then
        assertThat(partMaintenance).isEqualToComparingFieldByField(PartMaintenanceFixture.clutchReplace());
        assertThat(maintenanceRepository.findAll()).hasSize(1);
    }
}
