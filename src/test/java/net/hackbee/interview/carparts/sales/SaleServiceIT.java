package net.hackbee.interview.carparts.sales;

import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.SaleArgumentFixture;
import net.hackbee.interview.carparts.persistence.SaleArgumentRepository;
import net.hackbee.interview.carparts.persistence.entity.PartEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SaleService.class)
@ActiveProfiles("test")
public class SaleServiceIT {

    @Autowired
    private SaleArgumentRepository saleArgumentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SaleService subject;

    @Test
    public void cleanupExisting() {
        // given
        PartEntity partEntity = testEntityManager.persist(PartFixture.diskBrakes());
        testEntityManager.persist(SaleArgumentFixture.fastDeliveryAndEasyInstall(partEntity));

        // when
        boolean result = subject.cleanByPart(partEntity.getId());

        // then
        assertThat(result).isTrue();
        assertThat(saleArgumentRepository.findAll()).isEmpty();
    }

    @Test
    public void cleanupNotExisting() {
        // when
        boolean result = subject.cleanByPart(1L);

        // then
        assertThat(result).isFalse();
    }

}
