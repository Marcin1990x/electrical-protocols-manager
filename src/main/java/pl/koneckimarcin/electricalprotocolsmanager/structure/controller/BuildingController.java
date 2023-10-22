package pl.koneckimarcin.electricalprotocolsmanager.structure.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.BuildingDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.FloorDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.BuildingDtoRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.reposiitory.FloorDtoRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class BuildingController {

    private BuildingDtoRepository buildingDtoRepository;
    private FloorDtoRepository floorDtoRepository;

    public BuildingController(BuildingDtoRepository buildingDtoRepository, FloorDtoRepository floorDtoRepository) {
        this.buildingDtoRepository = buildingDtoRepository;
        this.floorDtoRepository = floorDtoRepository;
    }

    @GetMapping("/buildings")
    public List<BuildingDto> getBuildings() {

        return buildingDtoRepository.findAll();
    }
    @GetMapping("/buildings/{id}")
    public Optional<BuildingDto> getBuilding(@PathVariable int id) {

        return buildingDtoRepository.findById(id);
    }

    @PostMapping("/buildings")
    public BuildingDto addBuilding(@RequestBody BuildingDto building) {

        buildingDtoRepository.save(building);

        return building;
    }

    @PutMapping("/buildings/{buildingId}")
    public Optional<BuildingDto> addFloorToBuilding(@PathVariable  int buildingId, @RequestParam int floorId)
            throws InvalidObjectException {

        Optional<BuildingDto> building = addFloorToBuildingLogic(buildingId, floorId);
        buildingDtoRepository.save(building.get());

        return building;
    }

    private Optional<BuildingDto> addFloorToBuildingLogic(int buildingId, int floorId) throws InvalidObjectException {

        Optional<FloorDto> floor = floorDtoRepository.findById(floorId);
        Optional<BuildingDto> building =  buildingDtoRepository.findById(buildingId);

        if(floor.isPresent() && building.isPresent())
            building.get().addFloor(floor.get());
        else {
            throw new InvalidObjectException("Building with ID: " + buildingId + " or " +
                                        "Floor with ID: " + floorId + " not found.");
        }
        return building;
    }
}
