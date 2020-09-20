package net.hackbee.interview.carparts.brands;

import net.hackbee.interview.carparts.BrandFixture;
import net.hackbee.interview.carparts.ModelFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.brands.model.BrandProfile;
import net.hackbee.interview.carparts.brands.model.ModelPartMapper;
import net.hackbee.interview.carparts.brands.model.ModelPartMapperImpl;
import net.hackbee.interview.carparts.persistence.ModelRepository;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.persistence.entity.BrandEntity;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
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
@Import({ModelPartMapperImpl.class, BrandService.class})
@ActiveProfiles("test")
public class BrandServiceIT {

    @Autowired
    private TestEntityManager testEntityManager;
    
    @Autowired
    private BrandService subject;

    @Test
    public void fetchProfile() {
        // given
        BrandEntity brandEntity = BrandFixture.entityBMW();
        PartEntity partEntity = testEntityManager.persist(PartFixture.diskBrakes());
        testEntityManager.persist(ModelFixture.m8Entity(brandEntity, partEntity));
        testEntityManager.persist(brandEntity);

        // when
        BrandProfile bmw = subject.profile("BMW");

        // then
        assertThat(bmw).usingRecursiveComparison().isEqualTo(BrandFixture.profileBMW());
    }
}
