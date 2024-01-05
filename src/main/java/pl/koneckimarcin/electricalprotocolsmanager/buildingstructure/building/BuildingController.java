package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.ProjectRepository;

import java.io.InvalidObjectException;
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

        return service.getBuildings();
    }

    @GetMapping("/{id}")
    public List<Building> getBuildingById(@PathVariable int id) {

        return service.getBuildingById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllBuildings() {

        service.deleteAllBuildings();
    }

    @PostMapping
    public Building addBuilding(@RequestBody Building building) {

        return service.addBuilding(building);
    }

    @PutMapping("/{buildingId}")
    public Optional<Building> addFloorToBuilding(@PathVariable int buildingId, @RequestParam int floorId) throws InvalidObjectException {

        return service.addFloorToBuilding(buildingId, floorId);
    }


}
