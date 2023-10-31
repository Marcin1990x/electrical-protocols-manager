package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class FloorController {

    private FloorRepository floorRepository;
    private RoomRepository roomRepository;
    private BuildingRepository buildingRepository;

    public FloorController(FloorRepository floorRepository, RoomRepository roomRepository, BuildingRepository buildingRepository) {
        this.floorRepository = floorRepository;
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
    }

    @GetMapping("/floors")
    public List<Floor> getFloors() {

        return floorRepository.findAll();
    }

    @PostMapping("/floors")
    public Floor addFloor(@RequestBody Floor floor) {

        floorRepository.save(floor);

        return floor;
    }

    @DeleteMapping("/floors/{id}/{buildingId}")
    public void deleteById(@PathVariable int id, @PathVariable int buildingId) {

        Optional<Building> building = buildingRepository.findById(buildingId);
        Optional<Floor> floor = floorRepository.findById(id);
        building.get().removeFloor(floor.get());

        floorRepository.deleteById(id);
    }
    @PutMapping("/floors/{floorId}")
    public Optional<Floor> addRoomToFloor(@PathVariable  int floorId, @RequestParam int roomId)
            throws InvalidObjectException {

        Optional<Floor> floor = addRoomToFloorLogic(floorId, roomId);
        floorRepository.save(floor.get());

        return floor;
    }

    private Optional<Floor> addRoomToFloorLogic(int floorId, int roomId) throws InvalidObjectException {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<Floor> floor =  floorRepository.findById(floorId);

        if(floor.isPresent() && room.isPresent())
            floor.get().addRoom(room.get());
        else {
            throw new InvalidObjectException("Floor with ID: " + floorId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return floor;
    }
}
