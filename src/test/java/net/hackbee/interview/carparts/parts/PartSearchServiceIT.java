package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.BrandFixture;
import net.hackbee.interview.carparts.ModelFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartMapperImpl;
import net.hackbee.interview.carparts.persistence.entity.BrandEntity;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import({PartSearchService.class, PartMapperImpl.class})
@ActiveProfiles("test")
public class PartSearchServiceIT {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PartSearchService subject;

    @Test
    public void findByBrandAndModel() {
        BrandEntity brandEntity = testEntityManager.persist(BrandFixture.entityBMW());
        ModelEntity modelEntity = testEntityManager.persist(ModelFixture.partlessM8Entity(brandEntity));
        testEntityManager.persist(PartFixture.diskBrakes(modelEntity));
        testEntityManager.persist(PartFixture.steeringWheel(modelEntity));

        List<Part> parts = subject.findByBrandAndModel("BMW", "M8");

        assertThat(parts).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
                PartFixture.diskBrakesModel(),
                PartFixture.steeringWheelModel()
        );
    }

    @Test
    public void findByBrandAndModelAndDescription() {
        BrandEntity brandEntity = testEntityManager.persist(BrandFixture.entityBMW());
        ModelEntity modelEntity = testEntityManager.persist(ModelFixture.partlessM8Entity(brandEntity));
        testEntityManager.persist(PartFixture.diskBrakes(modelEntity));
        testEntityManager.persist(PartFixture.steeringWheel(modelEntity));

        List<Part> parts = subject.findByBrandAndModelAndDescription("BMW", "M8", "M Performance");

        assertThat(parts).usingFieldByFieldElementComparator().containsExactly(
                PartFixture.steeringWheelModel()
        );
    }

}
