package pl.koneckimarcin.electricalprotocolsmanager.structure.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BuildingRepository buildingRepository;


    @GetMapping("/buildings")
    public List<Project> getProjects() {

        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProject(@PathVariable int id) {

        return projectRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {

        projectRepository.deleteById(id);
    }

    @PostMapping()
    public Project addProject(@RequestBody Project project) {

        return projectRepository.save(project);
    }

    @PutMapping("/{projectId}")
    public Optional<Project> addFloorToBuilding(@PathVariable int projectId, @RequestParam int buildingId)
            throws InvalidObjectException {

        Optional<Project> project = projectRepository.findById(projectId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        project.get().setBuilding(building.get());

        projectRepository.save(project.get());

        return project;
    }
}
