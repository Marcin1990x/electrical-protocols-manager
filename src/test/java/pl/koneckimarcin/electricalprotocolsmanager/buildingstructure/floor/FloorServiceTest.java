package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.RoomRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
@Transactional
public class FloorServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorService floorService;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Value("${sql.script.create.floor}")
    private String sqlAddFloor;
    @Value("${sql.script.create.floor2}")
    private String sqlAddFloor2;
    @Value("${sql.script.create.building}")
    private String sqlAddBuilding;
    @Value("${sql.script.create.room}")
    private String sqlAddRoom;

    @Value("${sql.script.delete.floor}")
    private String sqlDeleteFloor;
    @Value("${sql.script.delete.building}")
    private String sqlDeleteBuilding;
    @Value("${sql.script.delete.room}")
    private String sqlDeleteRoom;

    @BeforeEach
    void setupDatabase() {

        System.setOut(new PrintStream(outputStreamCaptor));
        jdbc.execute(sqlAddFloor);
        jdbc.execute(sqlAddFloor2);
        jdbc.execute(sqlAddBuilding);
        jdbc.execute(sqlAddRoom);
    }

    @Test
    void shouldReturnAllFloors() {

        List<Floor> allFloors = floorService.getAllFloors();

        assertThat(allFloors, hasSize(2));
    }

    @Test
    void shouldSaveFloorToDatabase() {

        Floor floorToSave = new Floor();
        floorToSave.setFloorName("Saved Floor");

        floorService.addFloor(floorToSave);

        Optional<Floor> savedFloor = floorRepository.findById(3);

        assertTrue(savedFloor.isPresent());
        assertThat(savedFloor.get().getFloorName(), is("Saved Floor"));
        assertThat(floorRepository.findAll(), hasSize(3));
    }

    @Test
    void shouldDeleteFloorById() {

        assertTrue(floorRepository.findById(1).isPresent());
        assertTrue(buildingRepository.findById(1).isPresent());

        floorService.deleteFloorById(1, 1);

        assertFalse(floorRepository.findById(1).isPresent());
    }

    @Test
    void shouldNotDeleteFloorByIdNonValidBuildingId() {

        assertTrue(floorRepository.findById(1).isPresent());
        assertFalse(buildingRepository.findById(2).isPresent());

        floorService.deleteFloorById(1, 2);

        assertTrue(floorRepository.findById(1).isPresent());
    }

    @Test
    void shouldAddRoomToFloor() {

        assertTrue(floorRepository.findById(1).isPresent());
        assertThat(floorRepository.findById(1).get().getRooms(), hasSize(0));
        assertTrue(roomRepository.findById(1).isPresent());

        Optional<Floor> updatedFloor = floorService.addRoomToFloor(1, 1);

        assertThat(updatedFloor.get().getRooms(), hasSize(1));
        assertEquals("Kitchen", updatedFloor.get().getRooms().get(0).getRoomName());
    }
    @Test
    void shouldNotAddRoomToFloorNonValidRoomId() {

        String error = "Floor with ID: 1 or Room with ID: 2 not found.";

        assertTrue(floorRepository.findById(1).isPresent());
        assertThat(floorRepository.findById(1).get().getRooms(), hasSize(0));
        assertFalse(roomRepository.findById(2).isPresent());

        floorService.addRoomToFloor(1, 2);

        assertThat(floorRepository.findById(1).get().getRooms(), hasSize(0));
        assertTrue(outputStreamCaptor.toString().trim().contains(error));
    }

    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDeleteFloor);
        jdbc.execute(sqlDeleteBuilding);
        jdbc.execute(sqlDeleteRoom);
    }
}
