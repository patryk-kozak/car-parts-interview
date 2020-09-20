package net.hackbee.interview.carparts.maintenance;

import net.hackbee.interview.carparts.MaintenanceFixture;
import net.hackbee.interview.carparts.PartMaintenanceFixture;
import net.hackbee.interview.carparts.maintenance.model.PartMaintenance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(controllers = MaintenanceAPI.class)
public class MaintenanceAPIIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaintenanceService maintenanceServiceMock;

    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;

    @Test
    void allMaintenancesBetweenDates() throws Exception {
        given(maintenanceServiceMock.timeline(any(LocalDate.class), any(LocalDate.class)))
                .willReturn(Collections.singletonList(
                        MaintenanceFixture.septemberTwentyFirst()
                ));

        String contentAsString = mockMvc.perform(get("/api/maintenance/schedule")
                .param("beginDate", "2020-09-20")
                .param("endDate", "2020-09-22")
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build()
                        .writeValueAsString(Collections.singletonList(MaintenanceFixture.septemberTwentyFirst())));
    }

    @Test
    void createPartMaintenance() throws Exception {
        given(maintenanceServiceMock.registerPart(any(PartMaintenance.class)))
                .willReturn(PartMaintenanceFixture.clutchReplace());

        String contentAsString = mockMvc.perform(post("/api/maintenance/part")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapperBuilder.build().writeValueAsString(PartMaintenanceFixture.clutchReplace()))
        )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build().writeValueAsString(PartMaintenanceFixture.clutchReplace()));
    }
}
