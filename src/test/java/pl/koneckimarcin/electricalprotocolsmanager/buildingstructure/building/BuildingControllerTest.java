package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.ProjectRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
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
//        jdbc.execute(sqlAddFloor);
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

/*    @Test todo
    void deleteBuildingByIdHttpRequest() throws Exception {

        Project project = projectRepository.findByProjectName("project");
        Optional<Building> building = buildingRepository.findById(1);
        Assertions.assertTrue(building.isPresent());

        Optional<Building> building = buildingRepository.findById(1);

        Project project = projectRepository.findByProjectName("project");
        project.setBuilding(building.get());

        System.out.println(projectRepository.findByBuildingId(1));

        mock.perform(MockMvcRequestBuilders.delete("/buildings/{id}", 1))
                .andExpect(status().isOk());
    }*/


    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDelBuilding);
        jdbc.execute(sqlDelProject);
        jdbc.execute(sqlDeleteFloor);
    }
}
