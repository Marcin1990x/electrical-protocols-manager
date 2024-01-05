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

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

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
    void setupDatabase() {
        jdbc.execute(sqlAddBuilding);
        jdbc.execute(sqlAddBuilding2);
        jdbc.execute(sqlAddProject);
        jdbc.execute(sqlAddFloor);
    }

    @Test
    void shouldReturnAllBuildings() {
        List<Building> buildings = buildingService.getBuildings();

        assertThat(buildings, hasSize(2));
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
        assertThat(buildingRepository.findAll(), hasSize(1));
    }

    @Test
    void shouldDeleteAllBuildings() {

        assertThat(buildingRepository.findAll(), hasSize(2));

        buildingService.deleteAllBuildings();
        assertThat(buildingRepository.findAll(), hasSize(0));
    }

    @Test
    void shouldSaveBuildingToDatabase() {

        Building buildingToAdd = new Building();
        buildingToAdd.setBuildingName("Saved building");

        buildingService.addBuilding(buildingToAdd);

        Optional<Building> savedBuilding = buildingRepository.findById(3);

        assertTrue(savedBuilding.isPresent());
        assertThat(savedBuilding.get().getBuildingName(), is("Saved building"));
        assertThat(buildingRepository.findAll(), hasSize(3));
    }

    @Test
    void shouldAddFloorToBuilding() throws InvalidObjectException {

        assertTrue(buildingRepository.findById(1).isPresent());
        assertThat(buildingRepository.findById(1).get().getFloors(), hasSize(0));
        assertTrue(floorRepository.findById(1).isPresent());

        Optional<Building> updatedBuilding = buildingService.addFloorToBuilding(1, 1);

        assertThat(updatedBuilding.get().getFloors(), hasSize(1));
        assertEquals("Ground Floor", updatedBuilding.get().getFloors().get(0).getFloorName());
    }

    @Test
    void shouldThrowExceptionAndNotAddFloorToBuildingNonValidFloorId() {

        String errorMessage = "Building with ID: 1 or Floor with ID: 0 not found.";

        assertTrue(buildingRepository.findById(1).isPresent());
        assertThat(buildingRepository.findById(1).get().getFloors(), hasSize(0));
        assertFalse(floorRepository.findById(0).isPresent());


        InvalidObjectException exception = assertThrows(InvalidObjectException.class, () ->
                buildingService.addFloorToBuilding(1, 0));

        assertThat(buildingRepository.findById(1).get().getFloors(), hasSize(0));
        assertEquals(errorMessage, exception.getMessage());
    }

    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDelBuilding);
        jdbc.execute(sqlDelProject);
        jdbc.execute(sqlDeleteFloor);
    }
}
