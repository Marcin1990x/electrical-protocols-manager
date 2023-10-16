package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

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

    public int getRoomsCount() { // method if version each room on one separate page will be necessary

        int totalRoomsWithMeasurements = 0;

        for (Floor floor : floors) {
            for (Room room : floor.getRooms()) {
                if (room.getMeasurementMains().size() > 0) {
                    totalRoomsWithMeasurements++;
                }
            }
        }
        return totalRoomsWithMeasurements;
    }
    public List<String> extractMeasurementMainDistinctNames(){

        return this.getMeasurementMainList()
                .stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
