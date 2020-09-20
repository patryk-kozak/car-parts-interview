package net.hackbee.interview.carparts.parts;

import net.hackbee.interview.carparts.PartFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = PartsAPI.class)
class PartsAPIIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartSearchService partsSearchServiceMock;

    @MockBean
    private PartService partServiceMock;

    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;

    @Test
    void modify() throws Exception {
        given(partServiceMock.update(anyLong(), any()))
                .willReturn(PartFixture.diskBrakesModel());

        String contentAsString = mockMvc.perform(put("/api/parts/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapperBuilder.build().writeValueAsString(PartFixture.diskBrakesModel()))
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build()
                        .writeValueAsString(PartFixture.diskBrakesModel()));
    }

    @Test
    void partAvailability() throws Exception {
        given(partServiceMock.available(anyLong()))
                .willReturn(PartFixture.partAvailable());

        String contentAsString = mockMvc.perform(get("/api/parts/1/availability"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build()
                        .writeValueAsString(PartFixture.partAvailable()));
    }

    @Test
    void partsByBrandAndModel() throws Exception {
        given(partsSearchServiceMock.findByBrandAndModel(anyString(), anyString()))
                .willReturn(Collections.singletonList(PartFixture.diskBrakesModel()));

        String contentAsString = mockMvc.perform(get("/api/parts/BMW/M8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build()
                        .writeValueAsString(Collections.singletonList(PartFixture.diskBrakesModel())));
    }

    @Test
    void partsByBrandAndModelAndDescription() throws Exception {
        given(partsSearchServiceMock.findByBrandAndModelAndDescription(anyString(), anyString(), anyString()))
                .willReturn(Collections.singletonList(PartFixture.diskBrakesModel()));

        String contentAsString = mockMvc.perform(get("/api/parts/BMW/M8")
        .param("partDescription", "M"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(contentAsString)
                .isEqualTo(mapperBuilder.build()
                        .writeValueAsString(Collections.singletonList(PartFixture.diskBrakesModel())));
    }
}