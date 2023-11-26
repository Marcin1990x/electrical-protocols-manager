package pl.koneckimarcin.electricalprotocolsmanager.structure.project;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String projectName;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "building_id")
    private Building building;

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public Building getBuilding() {
        return building;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
