package net.hackbee.interview.carparts.brands;

import net.hackbee.interview.carparts.BrandFixture;
import net.hackbee.interview.carparts.ModelFixture;
import net.hackbee.interview.carparts.PartFixture;
import net.hackbee.interview.carparts.brands.model.BrandProfile;
import net.hackbee.interview.carparts.brands.model.ModelPartMapperImpl;
import net.hackbee.interview.carparts.persistence.ModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private ModelRepository modelRepositoryMock;
    @Mock
    private ModelPartMapperImpl modelPartMapperMock;

    @InjectMocks
    private BrandService subject;

    @Test
    void findModelsAndPartsByBrand() {
        String profileName = "BMW";
        given(modelRepositoryMock.findByBrand_name(profileName)).willReturn(
                Collections.singletonList(ModelFixture.m8Entity(BrandFixture.entityBMW(), PartFixture.diskBrakes()))
        );
        given(modelPartMapperMock.entityToModelPart(any())).willCallRealMethod();

        BrandProfile profile = subject.profile(profileName);

        assertThat(profile).usingRecursiveComparison().isEqualTo(BrandFixture.profileBMW());
        then(modelRepositoryMock).should().findByBrand_name(profileName);
        then(modelPartMapperMock).should().entityToModelPart(any());
    }

}