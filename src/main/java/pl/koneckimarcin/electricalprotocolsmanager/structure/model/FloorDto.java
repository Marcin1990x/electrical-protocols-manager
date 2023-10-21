package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import jakarta.persistence.*;

@Entity
public class FloorDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "floor_id")
    private int id;

    private String floorCascadeName;

    private String floorName;

    @ManyToOne
    //@JoinColumn(name = "building_id")
    private BuildingDto buildingDto;

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getId() {
        return id;
    }

    public String getFloorName() {
        return floorName;
    }

    public String getFloorCascadeName() {
        return floorCascadeName;
    }

    public void setFloorCascadeName(String buildingName) {
        this.floorCascadeName = buildingName + "/" + this.floorName;
    }
}
