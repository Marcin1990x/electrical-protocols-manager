package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.Project;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@TestPropertySource("/application-test.properties")
@SpringBootTest
@Transactional
public class BuildingServiceTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingService buildingService;

    @Value("${sql.script.create.building}")
    private String sqlAddBuilding;
    @Value("${sql.script.create.project}")
    private String sqlAddProject;

    @Value("${sql.script.delete.building}")
    private String sqlDelBuilding;
    @Value("${sql.script.delete.project}")
    private String sqlDelProject;

    @BeforeEach
    void setupDatabase() {
        jdbc.execute(sqlAddBuilding);
        jdbc.execute(sqlAddProject);
    }

    @Test
    void shouldReturnAllBuildings() {
        List<Building> buildings = buildingService.getBuildings();

        assertThat(buildings, hasSize(1));
    }

    @Test
    void shouldReturnBuildingById() {

        List<Building> building = buildingService.getBuildingById(1);

        assertThat(building, hasSize(1));
    }

    @Test
    void shouldDeleteBuildingById() {

        Optional<Building> building = buildingRepository.findById(1);
        Assertions.assertTrue(building.isPresent());

        Project project = projectRepository.findByProjectName("project");
        project.setBuilding(building.get());

        buildingService.deleteById(1);
        assertThat(buildingRepository.findAll(), hasSize(0));
    }

    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDelBuilding);
        jdbc.execute(sqlDelProject);
    }
}
