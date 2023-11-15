package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class CircuitInsulationResistanceTncEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private CircuitInsulationResistanceTncEntry entry = new CircuitInsulationResistanceTncEntry();

    @MockBean
    private CircuitInsulationResistanceTncEntryRepository repository;

    @Test
    void shouldReturnAllEntries() throws Exception {
        //given
        given(repository.findAll()).willReturn(List.of(entry));
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/2/entries"))
                .andExpect(status().is(200))
                .andReturn();
    }
}
