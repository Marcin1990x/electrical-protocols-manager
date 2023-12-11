package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.ProjectRepository;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("buildings")
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingService service;

    @GetMapping
    public List<Building> getBuildings() {

        return buildingRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Building> getBuilding(@PathVariable int id) {

        List<Building> buildings = new ArrayList<>();
        buildings.add(buildingRepository.findById(id).get());

        return buildings;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        service.deleteBuildingFromProject(id);
        buildingRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllBuildings() {

        buildingRepository.deleteAll();
    }

    @PostMapping
    public Building addBuilding(@RequestBody Building building) {

        buildingRepository.save(building);
        return building;
    }

    @PutMapping("/{buildingId}")
    public Optional<Building> addFloorToBuilding(@PathVariable int buildingId, @RequestParam int floorId)
            throws InvalidObjectException {

        Optional<Building> building = service.addFloorToBuildingLogic(buildingId, floorId);
        buildingRepository.save(building.get());

        return building;
    }
}
