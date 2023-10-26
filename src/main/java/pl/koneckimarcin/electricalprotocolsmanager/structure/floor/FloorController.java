package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class FloorController {

    private FloorRepository floorDtoRepository;
    private RoomRepository roomDtoRepository;

    public FloorController(FloorRepository floorDtoRepository, RoomRepository roomDtoRepository) {
        this.floorDtoRepository = floorDtoRepository;
        this.roomDtoRepository = roomDtoRepository;
    }

    @GetMapping("/floors")
    public List<Floor> getFloors() {

        return floorDtoRepository.findAll();
    }

    @PostMapping("/floors")
    public Floor addFloor(@RequestBody Floor floor) {

        floorDtoRepository.save(floor);

        return floor;
    }
    @PutMapping("/floors/{floorId}")
    public Optional<Floor> addRoomToFloor(@PathVariable  int floorId, @RequestParam int roomId)
            throws InvalidObjectException {

        Optional<Floor> floor = addRoomToFloorLogic(floorId, roomId);
        floorDtoRepository.save(floor.get());

        return floor;
    }

    private Optional<Floor> addRoomToFloorLogic(int floorId, int roomId) throws InvalidObjectException {

        Optional<Room> room = roomDtoRepository.findById(roomId);
        Optional<Floor> floor =  floorDtoRepository.findById(floorId);

        if(floor.isPresent() && room.isPresent())
            floor.get().addRoom(room.get());
        else {
            throw new InvalidObjectException("Floor with ID: " + floorId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return floor;
    }
}
