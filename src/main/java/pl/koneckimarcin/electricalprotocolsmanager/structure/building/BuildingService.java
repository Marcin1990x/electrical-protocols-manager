package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorRepository;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.ProjectRepository;

import java.io.InvalidObjectException;
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

        if(projectRepository.findByProjectName(projectName).getBuilding() != null) {
            int buildingId = projectRepository.findByProjectName(projectName).getBuilding().getId();
            return buildingRepository.findById(buildingId).get();
        } else return null;
    }

    public void deleteBuildingFromProject(int id) {
        projectRepository.findByBuildingId(id).setBuilding(null);
    }
    public Optional<Building> addFloorToBuildingLogic(int buildingId, int floorId) throws InvalidObjectException {

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
