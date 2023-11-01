package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    private RoomRepository roomRepository;
    private MeasurementMainRepository mainRepository;
    private FloorRepository floorRepository;

    public RoomController(RoomRepository roomRepository, MeasurementMainRepository mainRepository, FloorRepository floorRepository) {
        this.roomRepository = roomRepository;
        this.mainRepository = mainRepository;
        this.floorRepository = floorRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {

        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public Optional<Room> getRoom(@PathVariable int id) {

        return roomRepository.findById(id);
    }

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody Room room) {

        roomRepository.save(room);

        return room;
    }
    @DeleteMapping("/rooms/{id}/{floorId}")
    public void deleteById(@PathVariable int id, @PathVariable int floorId) {

        Optional<Floor> floor = floorRepository.findById(floorId);
        Optional<Room> room = roomRepository.findById(id);
        floor.get().removeRoom(room.get());
        roomRepository.deleteById(id);
    }

    @PutMapping("/rooms/{roomId}")
    public Optional<Room> addEntryToMain
            (@PathVariable int roomId, @RequestParam int mainId)
            throws InvalidObjectException {

        Optional<Room> room = addMainToRoomLogic(roomId, mainId);
        roomRepository.save(room.get());

        return room;
    }

    private Optional<Room> addMainToRoomLogic(int roomId, int mainId) throws InvalidObjectException {

        Optional<MeasurementMain> entry = mainRepository.findById(mainId);
        Optional<Room> room = roomRepository.findById(roomId);

        if (entry.isPresent() && room.isPresent())
            room.get().addMeasurementMain(entry.get());
        else {
            throw new InvalidObjectException("Measurement with ID: " + mainId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return room;
    }

}
