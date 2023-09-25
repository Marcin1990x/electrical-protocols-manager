package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
