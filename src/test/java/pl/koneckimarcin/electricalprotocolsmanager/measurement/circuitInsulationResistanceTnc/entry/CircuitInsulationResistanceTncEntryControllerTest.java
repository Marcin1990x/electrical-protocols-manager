package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
    @DisplayName("Should find all entries.")
    void shouldReturnAllEntries() throws Exception {
        //given
        given(repository.findAll()).willReturn(List.of(entry, entry, entry));
        //when
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/2/entries"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        String entriesJson = mvcResult.getResponse().getContentAsString();
        List<CircuitInsulationResistanceTncEntry> entries = new ObjectMapper()
                .readValue(entriesJson, new TypeReference<>() {});
        assertThat(entries, hasSize(3));
    }
    @Test
    @DisplayName("Should save entry")
    void shouldSaveNewEntry() throws Exception {

        //given
        entry.setL1l2(10);
        entry.setL2l3(10);
        entry.setL3l1(10);
        entry.setL1pen(10);
        entry.setL2pen(10);
        entry.setL3pen(10);
        entry.setRa(10); // do before each
        String entryJson = new ObjectMapper().writeValueAsString(entry);
        given(repository.save(entry)).willReturn(entry);
        //when
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/2/entries")
                        .content(entryJson)
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andReturn();
        //System.out.printl; // check if response body is ok
    }
}
