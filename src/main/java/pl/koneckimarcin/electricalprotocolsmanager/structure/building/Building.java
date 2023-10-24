package pl.koneckimarcin.electricalprotocolsmanager.structure.building;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Building {

    private List<Floor> floors = new ArrayList<>();

    private String buildingName;

    public Building(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {

        this.floors.add(floor);
        floor.setFloorCascadeName(this.buildingName);
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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
    public List<String> extractMeasurementMainDistinctNames(){

        return this.getMeasurementMainList()
                .stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .collect(Collectors.toList());
    }
}