package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project.ProjectRepository;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private FloorRepository floorRepository;

    public Building retrieveByProjectName(String projectName) {

        return projectRepository.findByProjectName(projectName).getBuilding();
    }

    public List<Building> getBuildings() {

        return buildingRepository.findAll();
    }

    public List<Building> getBuildingById(int id) {

        //done using list because it's UI client requirement
        List<Building> buildings = new ArrayList<>();

        Optional<Building> building = buildingRepository.findById(id);
        building.ifPresent(buildings::add);

        return buildings;
    }

    public void deleteById(int id) {

        deleteBuildingFromProject(id);
        buildingRepository.deleteById(id);
    }

    private void deleteBuildingFromProject(int id) {
        projectRepository.findByBuildingId(id).setBuilding(null);
    }

    public void deleteAllBuildings() {

        buildingRepository.deleteAll();
    }

    public Building addBuilding(Building building) {

        buildingRepository.save(building);
        return building;
    }

    public Optional<Building> addFloorToBuilding(int buildingId, int floorId) throws InvalidObjectException {

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
