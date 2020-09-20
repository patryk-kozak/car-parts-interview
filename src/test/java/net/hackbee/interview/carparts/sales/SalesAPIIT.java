package net.hackbee.interview.carparts.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = SalesAPI.class)
class SalesAPIIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService saleServiceMock;

    @Test
    void removePartSales() throws Exception {
        given(saleServiceMock.cleanByPart(1L)).willReturn(Boolean.TRUE);

        mockMvc.perform(delete("/api/sales/part/1"))
                .andExpect(status().isOk());
    }

    @Test
    void noContent() throws Exception {
        given(saleServiceMock.cleanByPart(1111L)).willReturn(Boolean.FALSE);

        mockMvc.perform(delete("/api/sales/part/1111"))
                .andExpect(status().isNoContent());
    }
}