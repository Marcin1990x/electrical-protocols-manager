package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.Project;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.ProjectRepository;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class BuildingControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mock;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Mock
    private ProjectRepository projectRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.building}")
    private String sqlAddBuilding;
    @Value("${sql.script.create.building2}")
    private String sqlAddBuilding2;
    @Value("${sql.script.create.project}")
    private String sqlAddProject;
    @Value("${sql.script.create.floor}")
    private String sqlAddFloor;

    @Value("${sql.script.delete.building}")
    private String sqlDelBuilding;
    @Value("${sql.script.delete.project}")
    private String sqlDelProject;
    @Value("${sql.script.delete.floor}")
    private String sqlDeleteFloor;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddBuilding);
        jdbc.execute(sqlAddBuilding2);
        jdbc.execute(sqlAddProject);
        jdbc.execute(sqlAddFloor);
    }

    @Test
    void getBuildingsHttpRequest() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/buildings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getBuildingByIdHttpRequest() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/buildings/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].buildingName", is("House")));
    }

    @Test
    void deleteBuildingByIdHttpRequest() throws Exception {

        Project project = projectRepository.findByProjectName("project");
        Optional<Building> building = buildingRepository.findById(1);

        Assertions.assertTrue(building.isPresent());

        project.setBuilding(building.get());

        mock.perform(MockMvcRequestBuilders.delete("/buildings/{id}", 1))
                .andExpect(status().isOk());

        Optional<Building> deletedBuilding = buildingRepository.findById(1);
        Assertions.assertFalse(deletedBuilding.isPresent());
    }

    @Test
    void deleteBuildingByIdHttpRequestNonValidId() throws Exception {

        Optional<Building> building = buildingRepository.findById(3);
        Assertions.assertFalse(building.isPresent());

        assertThat(buildingRepository.findAll(), hasSize(2));

        mock.perform(MockMvcRequestBuilders.delete("/buildings/{id}", 3))
                .andExpect(status().isOk());

        assertThat(buildingRepository.findAll(), hasSize(2));
    }

    @Test
    void deleteAllBuildingsHttpRequest() throws Exception {

        mock.perform(MockMvcRequestBuilders.delete("/buildings"))
                .andExpect(status().isOk());

        assertThat(buildingRepository.findAll(), hasSize(0));
    }

    @Test
    void addBuildingHttpRequest() throws Exception {

        Building building = new Building();
        building.setBuildingName("New building");

        mock.perform(MockMvcRequestBuilders.post("/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(building)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.buildingName", is("New building")));

        Assertions.assertTrue(buildingRepository.findById(3).isPresent());
        assertThat(buildingRepository.findAll(), hasSize(3));
    }

    @Test
    void addFloorToBuildingHttpRequest() throws Exception {

        mock.perform(MockMvcRequestBuilders.put("/buildings/{buildingId}", 1)
                        .param("floorId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.floors", hasSize(1)))
                .andExpect(jsonPath("$.floors[0].floorName", is("Ground Floor")));
    }

    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDelProject);
        jdbc.execute(sqlDelBuilding);
        jdbc.execute(sqlDelProject);
        jdbc.execute(sqlDeleteFloor);
    }
}
