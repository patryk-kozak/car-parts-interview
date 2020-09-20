package net.hackbee.interview.carparts.brands;

import net.hackbee.interview.carparts.BrandFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = BrandsAPI.class)
class BrandsAPIIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandServiceMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void brandProfileReturn() throws Exception {
        given(brandServiceMock.profile("BMW")).willReturn(BrandFixture.profileBMW());

        String contentAsString = mockMvc.perform(get("/api/brands/BMW"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString).isEqualTo(objectMapper.writeValueAsString(BrandFixture.profileBMW()));
    }
}