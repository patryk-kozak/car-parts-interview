package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.PartNotFoundException;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartAvailability;
import net.hackbee.interview.carparts.parts.model.PartMapperImpl;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.warehouse.FakeWarehouseAPI;
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
@Import({PartService.class, PartMapperImpl.class, FakeWarehouseAPI.class})
@ActiveProfiles("test")
public class PartServiceIT {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PartService subject;

    @Test
    public void availability() {
        PartAvailability partAvailability = subject.available(1L);

        assertThat(partAvailability.getAvailable()).isTrue();
    }
    
    @Test
    public void update() throws PartNotFoundException {
        testEntityManager.persist(PartFixture.diskBrakes());

        Part part = subject.update(1L, PartFixture.updatedDiskBrakesModel());
        
        assertThat(part).isEqualToComparingFieldByField(PartFixture.updatedDiskBrakesModel());
        assertThat(partRepository.getOne(1L).getName()).isEqualTo("Updated disk brakes");
        assertThat(partRepository.getOne(1L).getDescription()).isEqualTo("Awesome updated brakes");
    }
    
}
