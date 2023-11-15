package pl.koneckimarcin.electricalprotocolsmanager.structure.building;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Building building = new Building();

    @MockBean
    private BuildingRepository repository;

    @Test
    void shouldReturnAllEntries() throws Exception {
        //given
        given(repository.findAll()).willReturn(List.of(building));
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/buildings"))
                .andExpect(status().is(200))
                .andReturn();
    }
}
