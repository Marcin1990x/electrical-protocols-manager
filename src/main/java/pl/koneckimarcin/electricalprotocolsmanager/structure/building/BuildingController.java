package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
public class BuildingController {

    private BuildingRepository buildingRepository;
    private FloorRepository floorDtoRepository;

    public BuildingController(BuildingRepository buildingRepository, FloorRepository floorDtoRepository) {
        this.buildingRepository = buildingRepository;
        this.floorDtoRepository = floorDtoRepository;
    }

    @GetMapping("/buildings")
    public List<Building> getBuildings() {

        return buildingRepository.findAll();
    }
    @GetMapping("/buildings/{id}")
    public Optional<Building> getBuilding(@PathVariable int id) {

        return buildingRepository.findById(id);
    }

    @DeleteMapping("/buildings/{id}")
    public void deleteById(@PathVariable int id) {

        buildingRepository.deleteById(id);
    }

    @DeleteMapping("/buildings")
    public void deleteAllBuildings() {

        buildingRepository.deleteAll();
    }

    @PostMapping("/buildings")
    public ResponseEntity<String> addBuilding(@RequestBody Building building) {

        if(buildingRepository.findAll().size() == 0) {
            buildingRepository.save(building);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error 102. You can create only one building.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/buildings/{buildingId}")
    public Optional<Building> addFloorToBuilding(@PathVariable  int buildingId, @RequestParam int floorId)
            throws InvalidObjectException {

        Optional<Building> building = addFloorToBuildingLogic(buildingId, floorId);
        buildingRepository.save(building.get());

        return building;
    }

    private Optional<Building> addFloorToBuildingLogic(int buildingId, int floorId) throws InvalidObjectException {

        Optional<Floor> floor = floorDtoRepository.findById(floorId);
        Optional<Building> building =  buildingRepository.findById(buildingId);

        if(floor.isPresent() && building.isPresent())
            building.get().addFloor(floor.get());
        else {
            throw new InvalidObjectException("Building with ID: " + buildingId + " or " +
                                        "Floor with ID: " + floorId + " not found.");
        }
        return building;
    }
}
