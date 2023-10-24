package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDtoRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    private RoomDtoRepository roomDtoRepository;
    private MeasurementMainDtoRepository mainDtoRepository;

    public RoomController(RoomDtoRepository roomDtoRepository, MeasurementMainDtoRepository mainDtoRepository) {
        this.roomDtoRepository = roomDtoRepository;
        this.mainDtoRepository = mainDtoRepository;
    }

    @GetMapping("/rooms")
    public List<pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto> getRooms() {

        return roomDtoRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public Optional<pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto> getRoom(@PathVariable int id) {

        return roomDtoRepository.findById(id);
    }

    @PostMapping("/rooms")
    public pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto addRoom(@RequestBody pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto room) {

        roomDtoRepository.save(room);

        return room;
    }

    @PutMapping("/rooms/{roomId}")
    public Optional<pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto> addEntryToMain
            (@PathVariable int roomId, @RequestParam int mainId)
            throws InvalidObjectException {

        Optional<RoomDto> room = addMainToRoomLogic(roomId, mainId);
        roomDtoRepository.save(room.get());

        return room;
    }

    private Optional<RoomDto> addMainToRoomLogic(int roomId, int mainId) throws InvalidObjectException {

        Optional<MeasurementMainDto> entry = mainDtoRepository.findById(mainId);
        Optional<RoomDto> room = roomDtoRepository.findById(roomId);

        if (entry.isPresent() && room.isPresent())
            room.get().addMeasurementMain(entry.get());
        else {
            throw new InvalidObjectException("Measurement with ID: " + mainId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return room;
    }

}
