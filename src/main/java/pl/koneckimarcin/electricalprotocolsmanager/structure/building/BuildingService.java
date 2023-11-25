package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.structure.project.ProjectRepository;

@Service
public class BuildingService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    public Building retrieveByProjectName(String projectName) {

        if(projectRepository.findByProjectName(projectName).getBuilding() != null) {
            int buildingId = projectRepository.findByProjectName(projectName).getBuilding().getId();
            return buildingRepository.findById(buildingId).get();
        } else return null;
    }
}
