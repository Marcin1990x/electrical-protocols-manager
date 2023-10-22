package pl.koneckimarcin.electricalprotocolsmanager.structure.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.BuildingDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.FloorDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.RoomDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.BuildingDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.FloorDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.RoomDtoRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class FloorController {

    private FloorDtoRepository floorDtoRepository;
    private RoomDtoRepository roomDtoRepository;

    public FloorController(FloorDtoRepository floorDtoRepository, RoomDtoRepository roomDtoRepository) {
        this.floorDtoRepository = floorDtoRepository;
        this.roomDtoRepository = roomDtoRepository;
    }

    @GetMapping("/floors")
    public List<FloorDto> getFloors() {

        return floorDtoRepository.findAll();
    }

    @PostMapping("/floors")
    public FloorDto addFloor(@RequestBody FloorDto floor) {

        floorDtoRepository.save(floor);

        return floor;
    }
    @PutMapping("/floors/{floorId}")
    public Optional<FloorDto> addRoomToFloor(@PathVariable  int floorId, @RequestParam int roomId)
            throws InvalidObjectException {

        Optional<FloorDto> floor = addRoomToFloorLogic(floorId, roomId);
        floorDtoRepository.save(floor.get());

        return floor;
    }

    private Optional<FloorDto> addRoomToFloorLogic(int floorId, int roomId) throws InvalidObjectException {

        Optional<RoomDto> room = roomDtoRepository.findById(roomId);
        Optional<FloorDto> floor =  floorDtoRepository.findById(floorId);

        if(floor.isPresent() && room.isPresent())
            floor.get().addRoom(room.get());
        else {
            throw new InvalidObjectException("Floor with ID: " + floorId + " or " +
                    "Room with ID: " + roomId + " not found.");
        }
        return floor;
    }
}
