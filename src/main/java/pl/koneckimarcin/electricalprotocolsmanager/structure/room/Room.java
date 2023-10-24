package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private List<MeasurementMain> measurementMains = new ArrayList<>();

    private String roomName;

    private String roomCascadeName;

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public List<MeasurementMain> getMeasurementMains() {
        return measurementMains;
    }

    public void addMeasurementMain(MeasurementMain measurementMain) {
        this.measurementMains.add(measurementMain);
        measurementMain.setMeasurementMainCascadeName(this.roomCascadeName);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCascadeName() {
        return roomCascadeName;
    }

    public void setRoomCascadeName(String floorCascadeName) {

        this.roomCascadeName = floorCascadeName + "/" + this.roomName;
    }
}