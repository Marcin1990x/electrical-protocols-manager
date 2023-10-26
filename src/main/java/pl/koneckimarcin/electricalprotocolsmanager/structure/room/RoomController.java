package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    private RoomRepository roomDtoRepository;
    private MeasurementMainRepository mainDtoRepository;

    public RoomController(RoomRepository roomDtoRepository, MeasurementMainRepository mainDtoRepository) {
        this.roomDtoRepository = roomDtoRepository;
        this.mainDtoRepository = mainDtoRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {

        return roomDtoRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public Optional<Room> getRoom(@PathVariable int id) {

        return roomDtoRepository.findById(id);
    }

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody Room room) {

        roomDtoRepository.save(room);

        return room;
    }

    @PutMapping("/rooms/{roomId}")
    public Optional<Room> addEntryToMain
            (@PathVariable int roomId, @RequestParam int mainId)
            throws InvalidObjectException {

        Optional<Room> room = addMainToRoomLogic(roomId, mainId);
        roomDtoRepository.save(room.get());

        return room;
    }

    private Optional<Room> addMainToRoomLogic(int roomId, int mainId) throws InvalidObjectException {

        Optional<MeasurementMain> entry = mainDtoRepository.findById(mainId);
        Optional<Room> room = roomDtoRepository.findById(roomId);

        if (entry.isPresent() && room.isPresent())
            room.get().addMeasurementMain(entry.get());
        else {
            throw new InvalidObjectException("Measurement with ID: " + mainId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return room;
    }

}
