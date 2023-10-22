package pl.koneckimarcin.electricalprotocolsmanager.structure.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.BuildingDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.FloorDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.RoomDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.FloorDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.RoomDtoRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    private RoomDtoRepository roomDtoRepository;

    public RoomController(RoomDtoRepository roomDtoRepository) {
        this.roomDtoRepository = roomDtoRepository;
    }

    @GetMapping("/rooms")
    public List<RoomDto> getRooms() {

        return roomDtoRepository.findAll();
    }
    @GetMapping("/rooms/{id}")
    public Optional<RoomDto> getRoom(@PathVariable int id) {

        return roomDtoRepository.findById(id);
    }

    @PostMapping("/rooms")
    public RoomDto addBuildings(@RequestBody RoomDto room) {

        roomDtoRepository.save(room);

        return room;
    }
}
