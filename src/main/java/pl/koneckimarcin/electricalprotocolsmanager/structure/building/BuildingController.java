package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class BuildingController {

    private BuildingRepository buildingDtoRepository;
    private FloorRepository floorDtoRepository;

    public BuildingController(BuildingRepository buildingDtoRepository, FloorRepository floorDtoRepository) {
        this.buildingDtoRepository = buildingDtoRepository;
        this.floorDtoRepository = floorDtoRepository;
    }

    @GetMapping("/buildings")
    public List<Building> getBuildings() {

        return buildingDtoRepository.findAll();
    }
    @GetMapping("/buildings/{id}")
    public Optional<Building> getBuilding(@PathVariable int id) {

        return buildingDtoRepository.findById(id);
    }

    @PostMapping("/buildings")
    public Building addBuilding(@RequestBody Building building) {

        buildingDtoRepository.save(building);

        return building;
    }

    @PutMapping("/buildings/{buildingId}")
    public Optional<Building> addFloorToBuilding(@PathVariable  int buildingId, @RequestParam int floorId)
            throws InvalidObjectException {

        Optional<Building> building = addFloorToBuildingLogic(buildingId, floorId);
        buildingDtoRepository.save(building.get());

        return building;
    }

    private Optional<Building> addFloorToBuildingLogic(int buildingId, int floorId) throws InvalidObjectException {

        Optional<Floor> floor = floorDtoRepository.findById(floorId);
        Optional<Building> building =  buildingDtoRepository.findById(buildingId);

        if(floor.isPresent() && building.isPresent())
            building.get().addFloor(floor.get());
        else {
            throw new InvalidObjectException("Building with ID: " + buildingId + " or " +
                                        "Floor with ID: " + floorId + " not found.");
        }
        return building;
    }
}
