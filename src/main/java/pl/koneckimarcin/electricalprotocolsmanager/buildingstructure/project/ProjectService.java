package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.BuildingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    public List<Project> getProjects() {

        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(int projectId) {

        return projectRepository.findById(projectId);
    }

    public Project getProjectByName(String projectName) {

        return projectRepository.findByProjectName(projectName);
    }

    public void deleteProjectById(int projectId) {

        projectRepository.deleteById(projectId);
    }

    public void deleteProjectByName(String projectName) {

        Project project = projectRepository.findByProjectName(projectName);
        projectRepository.deleteById(project.getId());
    }

    public Project addProject(Project project) {

        return projectRepository.save(project);
    }

    public Project addBuildingToProject(String projectName, int buildingId) {

        Project project = projectRepository.findByProjectName(projectName);
        Optional<Building> building = buildingRepository.findById(buildingId);
        project.setBuilding(building.get());

        projectRepository.save(project);

        return project;
    }
}
