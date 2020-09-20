package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.BrandFixture;
import net.hackbee.interview.carparts.ModelFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.parts.model.Part;
import net.hackbee.interview.carparts.parts.model.PartMapperImpl;
import net.hackbee.interview.carparts.persistence.PartRepository;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PartSearchServiceTest {

    @Mock
    private PartRepository partRepositoryMock;
    @Mock
    private PartMapperImpl partMapperMock;

    @InjectMocks
    private PartSearchService subject;

    @BeforeEach
    void each() {
        given(partMapperMock.entityToPart(any())).willCallRealMethod();
    }

    @Test
    void findByBrandAndModel() {
        ModelEntity bmwM8Model = ModelFixture.partlessM8Entity(BrandFixture.entityBMW());
        given(partRepositoryMock.findByBrandAndModel(anyString(), anyString()))
                .willReturn(Collections.singletonList(
                        PartFixture.steeringWheel(bmwM8Model)
                ));

        List<Part> brandAndModel = subject.findByBrandAndModel("BMW", "M8");

        assertThat(brandAndModel).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
                PartFixture.steeringWheelModel()
        );
    }

    @Test
    void findByBrandAndModelAndDescription() {
        ModelEntity bmwM8Model = ModelFixture.partlessM8Entity(BrandFixture.entityBMW());
        given(partRepositoryMock.findByBrandAndModel(anyString(), anyString()))
                .willReturn(List.of(
                        PartFixture.steeringWheel(bmwM8Model),
                        PartFixture.diskBrakes(bmwM8Model)
                ));

        List<Part> brandAndModel = subject.findByBrandAndModelAndDescription("BMW", "M8", "M Performance");

        assertThat(brandAndModel).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
                PartFixture.steeringWheelModel()
        );
    }
}