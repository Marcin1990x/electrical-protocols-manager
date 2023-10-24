package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BuildingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String buildingName;

    @OneToMany
    private List<FloorDto> floors = new ArrayList<>();

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

    public List<MeasurementMainDto> getMeasurementMainList() {

        List<MeasurementMainDto> measurementMainList = new ArrayList<>();
        for (FloorDto floor : getFloors()) {
            for (RoomDto room : floor.getRooms()) {
                measurementMainList.addAll(room.getMeasurementMains());
            }
        }
        return measurementMainList;
    }
    public List<String> extractMeasurementMainDistinctNames() {

        return this.getMeasurementMainList()
                .stream()
                .map(MeasurementMainDto::getMeasurementName)
                .distinct()
                .collect(Collectors.toList());
    }
}
