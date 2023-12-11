package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("floors")
public class FloorController {

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private FloorService floorService;

    @GetMapping()
    public List<Floor> getFloors() {

        return floorService.getAllFloors();
    }

    @PostMapping()
    public Floor addFloor(@RequestBody Floor floor) {

        floorService.addFloor(floor);
        return floor;
    }

    @DeleteMapping("/{floorId}/{buildingId}")
    public void deleteFloorById(@PathVariable int floorId, @PathVariable int buildingId) {

        floorService.deleteFloorById(floorId, buildingId);
    }

    @PutMapping("/{floorId}")
    public Optional<Floor> addRoomToFloor(@PathVariable int floorId, @RequestParam int roomId) {

        return floorService.addRoomToFloor(floorId, roomId);
    }
}
