package pl.koneckimarcin.electricalprotocolsmanager.structure.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    public Project findByProjectName(String projectName);

    public Project findByBuildingId(int buildingId);
}
