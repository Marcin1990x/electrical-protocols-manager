package pl.koneckimarcin.electricalprotocolsmanager.structure.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.BuildingDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.FloorDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.BuildingDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.FloorDtoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StructureController {

    private BuildingDtoRepository buildingDtoRepository;
    private FloorDtoRepository floorDtoRepository;

    public StructureController(BuildingDtoRepository buildingDtoRepository, FloorDtoRepository floorDtoRepository) {
        this.buildingDtoRepository = buildingDtoRepository;
        this.floorDtoRepository = floorDtoRepository;
    }

    @GetMapping("/buildings")
    public List<BuildingDto> getBuildings() {

        return buildingDtoRepository.findAll();
    }

    @PostMapping("/buildings")
    public BuildingDto addBuiliding(@RequestBody BuildingDto building) {

        buildingDtoRepository.save(building);

        return building;
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
    @PatchMapping("/floors")
    public BuildingDto addFloorToBuilding(@RequestParam int buildingId, int floorId) {

        Optional<FloorDto> floor = floorDtoRepository.findById(floorId);

        Optional<BuildingDto> building =  buildingDtoRepository.findById(buildingId);

        building.get().addFloor(floor.get()); // do it with if

        buildingDtoRepository.save(building.get());

        return building.get();
    }
}
