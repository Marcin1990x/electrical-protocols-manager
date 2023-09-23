package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<Floor> floors;

    private String buildingName;

    public Building(List<Floor> floors, String buildingName) {
        this.floors = floors;
        this.buildingName = buildingName;

        for(Floor floor : floors){
            setFloorCascadeName(this.buildingName, floor);
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<MeasurementMain> getMeasurementMainList() {

        List<MeasurementMain> measurementMainList = new ArrayList<>();
        for(Floor floor : getFloors()){
            for(Room room : floor.getRooms()){
                measurementMainList.addAll(room.getMeasurementMains());
            }
        }
        return measurementMainList;
    }

    private void setFloorCascadeName(String buildingName, Floor floor){
        floor.setFloorCascadeName(buildingName);
    }

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
