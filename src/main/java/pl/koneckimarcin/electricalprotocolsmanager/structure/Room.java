package pl.koneckimarcin.electricalprotocolsmanager.structure;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

import java.util.List;

public class Room {

    private List<MeasurementMain> measurementMains;

    private String name;

    public Room(List<MeasurementMain> measurementMains, String name) {
        this.measurementMains = measurementMains;
        this.name = name;
    }

    public List<MeasurementMain> getMeasurementMains() {
        return measurementMains;
    }

    public void setMeasurementMains(List<MeasurementMain> measurementMains) {
        this.measurementMains = measurementMains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "measurementMains=" + measurementMains +
                ", name='" + name + '\'' +
                '}';
    }
}
