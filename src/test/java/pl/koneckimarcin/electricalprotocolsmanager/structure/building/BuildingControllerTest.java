package pl.koneckimarcin.electricalprotocolsmanager.structure.building;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    private final Building building = new Building();

    @MockBean
    private BuildingRepository repository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Should return all buildings")
    public void shouldReturnAllBuildings() throws Exception {
        //given
        given(repository.findAll()).willReturn(List.of(building, building));
        //then
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/buildings"))
                .andExpect(status().is(200))
                .andReturn();
        //then
        String buildingsJson = mvcResult.getResponse().getContentAsString();
        List<Building> buildings = new ObjectMapper()
                .readValue(buildingsJson, new TypeReference<>() {
                });

        assertThat(buildings, hasSize(2));
    }

    @Test
    @DisplayName("Should return List of one building with given id")
    public void shouldReturnListOfOneBuildingById() throws Exception {
        //given
        building.setBuildingName("Home");
        given(repository.findById(0)).willReturn(Optional.of(building));
        //then
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/buildings/0")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("buildingName").value("Home"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String buildingJson = mvcResult.getResponse().getContentAsString();
        List<Building> building = new ObjectMapper()
                .readValue(buildingJson, new TypeReference<>() {
                });
        assertThat(building, hasSize(1));

    }

}
