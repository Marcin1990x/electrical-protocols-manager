package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.ProjectRepository;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingService service;

    @GetMapping("/buildings")
    public List<Building> getBuildings() {

        return buildingRepository.findAll();
    }

    @GetMapping("/buildings/{id}")
    public List<Building> getBuilding(@PathVariable int id) {

        List<Building> buildings = new ArrayList<>();
        buildings.add(buildingRepository.findById(id).get());

        return buildings;
    }

    @DeleteMapping("/buildings/{id}")
    public void deleteById(@PathVariable int id) {

        projectRepository.findByBuildingId(id).setBuilding(null);
        buildingRepository.deleteById(id);
    }

    @DeleteMapping("/buildings")
    public void deleteAllBuildings() {

        buildingRepository.deleteAll();
    }

    @PostMapping("/buildings")
    public Building addBuilding(@RequestBody Building building) {

        buildingRepository.save(building);
        return building;
    }

    @PutMapping("/buildings/{buildingId}")
    public Optional<Building> addFloorToBuilding(@PathVariable int buildingId, @RequestParam int floorId)
            throws InvalidObjectException {

        Optional<Building> building = addFloorToBuildingLogic(buildingId, floorId);
        buildingRepository.save(building.get());

        return building;
    }

    private Optional<Building> addFloorToBuildingLogic(int buildingId, int floorId) throws InvalidObjectException {

        Optional<Floor> floor = floorRepository.findById(floorId);
        Optional<Building> building = buildingRepository.findById(buildingId);

        if (floor.isPresent() && building.isPresent())
            building.get().addFloor(floor.get());
        else {
            throw new InvalidObjectException("Building with ID: " + buildingId + " or " +
                    "Floor with ID: " + floorId + " not found.");
        }
        return building;
    }
}
