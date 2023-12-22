package pl.koneckimarcin.electricalprotocolsmanager.structure.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public List<Project> getProjects() {

        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable int id) {

        return projectService.getProjectById(id);
    }

    @GetMapping("/name={projectName}")
    public Project getProjectByName(@PathVariable String projectName) {

        return projectService.getProjectByName(projectName);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable int id) {

        projectService.deleteProjectById(id);
    }

    @DeleteMapping("/name={projectName}")
    public void deleteProjectByName(@PathVariable String projectName) {

        projectService.deleteProjectByName(projectName);
    }

    @PostMapping()
    public Project addProject(@RequestBody Project project) {

        return projectService.addProject(project);
    }

    @PutMapping("/{projectName}")
    public Project addBuildingToProject(@PathVariable String projectName, @RequestParam int buildingId)
            throws InvalidObjectException {

        return projectService.addBuildingToProject(projectName, buildingId);
    }
}
