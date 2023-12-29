package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class FloorControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.floor}")
    private String sqlAddFloor;
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
        jdbc.execute(sqlAddFloor);
        jdbc.execute(sqlAddBuilding);
        jdbc.execute(sqlAddRoom);
    }

    @Test
    void getFloorsHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/floors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void addFloorHttpRequest() throws Exception {

        Floor floor = new Floor();
        floor.setFloorName("Second Floor");

        mockMvc.perform(MockMvcRequestBuilders.post("/floors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(floor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.floorName", is("Second Floor")));

        Optional<Floor> verifyFloor = floorRepository.findById(2);
        Assertions.assertTrue(verifyFloor.isPresent());
    }

    @Test
    void deleteFloorHttpRequest() throws Exception {

        Assertions.assertTrue(floorRepository.findById(1).isPresent());

        mockMvc.perform(MockMvcRequestBuilders.delete("/floors/{floorId}/{buildingId}", 1, 1))
                .andExpect(status().isOk());

        Optional<Floor> deletedFloor = floorRepository.findById(1);
        Assertions.assertFalse(deletedFloor.isPresent());
    }
//    @PutMapping("/{floorId}")
//    public Optional<Floor> addRoomToFloor(@PathVariable int floorId, @RequestParam int roomId) {
//
//        return floorService.addRoomToFloor(floorId, roomId);
    @Test
    void addRoomToFloorHttpRequest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/floors/{floorId}", 1)
                .param("roomId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rooms", hasSize(1)))
                .andExpect(jsonPath("$.rooms[0].roomName", is("Kitchen")));
    }

    @AfterEach
    void cleanDatabase() {
        jdbc.execute(sqlDeleteFloor);
        jdbc.execute(sqlDeleteBuilding);
        jdbc.execute(sqlDeleteRoom);
    }
}
