package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String buildingName;

    @OneToMany
    private List<Floor> floors = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void addFloor(Floor floor) {

        this.floors.add(floor);
        floor.setFloorCascadeName(this.buildingName);
    }

    public List<MeasurementMain> getMeasurementMainList() {

        List<MeasurementMain> measurementMainList = new ArrayList<>();
        for (Floor floor : getFloors()) {
            for (Room room : floor.getRooms()) {
                measurementMainList.addAll(room.getMeasurementMains());
            }
        }
        return measurementMainList;
    }

    public List<String> extractMeasurementMainDistinctNames() {

        return this.getMeasurementMainList()
                .stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .collect(Collectors.toList());
    }
}
