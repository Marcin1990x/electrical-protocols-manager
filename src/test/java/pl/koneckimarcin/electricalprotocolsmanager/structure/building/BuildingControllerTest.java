package pl.koneckimarcin.electricalprotocolsmanager.structure.building;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Building building = new Building();

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private BuildingRepository repository;

    @MockBean
    private BuildingService buildingService;

    @Test
    @DisplayName("Should return all buildings.")
    public void shouldReturnAllBuildings() throws Exception {
        //given
        given(repository.findAll()).willReturn(List.of(building, building));
        //then
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/buildings"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        String buildingsJson = mvcResult.getResponse().getContentAsString();
        List<Building> buildings = mapper
                .readValue(buildingsJson, new TypeReference<>() {
                });
        assertThat(buildings, hasSize(2));
    }

    @Test
    @DisplayName("Should return List of one building with given id.")
    public void shouldReturnListOfOneBuildingById() throws Exception {
        //given
        building.setBuildingName("Home");
        given(repository.findById(0)).willReturn(Optional.of(building));
        //when then
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/buildings/0")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(0))
                .andExpect(jsonPath("$[*].buildingName").value("Home"))
                .andDo(print())
                .andReturn();

        String buildingJson = mvcResult.getResponse().getContentAsString();
        List<Building> building = mapper
                .readValue(buildingJson, new TypeReference<>() {
                });
        assertThat(building, hasSize(1));
    }

    @Test
    @DisplayName("Should delete building by id with correct status.")
    public void shouldDeleteBuildingById() throws Exception {
        //given
        doNothing().when(buildingService).deleteBuildingFromProject(1);
        //when then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/buildings/1")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete all buildings with correct status.")
    public void shouldDeleteAllBuildings() throws Exception {
        //given
        doNothing().when(buildingService).deleteBuildingFromProject(1);
        //when then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/buildings")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Should return added building and correct status.")
    public void shouldAddBuilding() throws Exception {
        //given
        building.setBuildingName("House");
        //when then
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/buildings")
                        .content(mapper.writeValueAsString(building))
                        .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingName").value("House"))
                .andReturn();
    }
    @Test
    @DisplayName("Should return bad request status when post without request body.")
    public void shouldThrowBadRequest() throws Exception {
        //given
        //when then
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/buildings")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
//    @Test
//    @Sql(scripts = "testData.sql")
//    @DisplayName("Should return building with added floor and correct status")
//    public void shouldReturnBuildingWithAddedFloor() throws Exception {
//        //given
//        Building building1 = new Building();
//        building1.setBuildingName("Test");
//        Floor testFloor = new Floor();
//        testFloor.setFloorName("TestFloor");
//        building1.addFloor(testFloor);
//        given(buildingService.addFloorToBuildingLogic(0, 0)).willReturn(Optional.of(building1));
//
//        //when then
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .put("/buildings/0")
//                        .param("floorId", "0")
//                )
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
