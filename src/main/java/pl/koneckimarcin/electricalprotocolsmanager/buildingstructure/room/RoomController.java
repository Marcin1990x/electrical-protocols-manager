package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getRooms() {

        return roomService.getRooms();
    }

    @GetMapping("/rooms/{id}")
    public Optional<Room> getRoomById(@PathVariable int id) {

        return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody Room room) {

        return roomService.addRoom(room);
    }

    @DeleteMapping("/rooms/{id}/{floorId}")
    public void deleteById(@PathVariable int id, @PathVariable int floorId) {

        roomService.deleteById(id, floorId);
    }

    @PutMapping("/rooms/{roomId}")
    public Optional<Room> addMainToRoom
            (@PathVariable int roomId, @RequestParam int mainId) throws InvalidObjectException {

        return roomService.addMainToRoom(roomId, mainId);
    }
}
