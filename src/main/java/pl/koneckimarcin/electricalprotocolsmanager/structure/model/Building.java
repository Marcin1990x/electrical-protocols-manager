package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<Floor> floors;

    private String name;

    public Building(List<Floor> floors, String name) {
        this.floors = floors;
        this.name = name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Building{" +
                "floors=" + floors +
                ", name='" + name + '\'' +
                '}';
    }
}
