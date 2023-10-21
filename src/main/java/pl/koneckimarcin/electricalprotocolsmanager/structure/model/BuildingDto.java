package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BuildingDto {

    @Id
    //@Column(name = "building_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String buildingName;

    @OneToMany
    private List<FloorDto> floors;

    public int getId() {
        return id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public List<FloorDto> getFloors() {
        return floors;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void addFloor(FloorDto floor) {

        this.floors.add(floor);
        floor.setFloorCascadeName(this.buildingName);
    }
}
