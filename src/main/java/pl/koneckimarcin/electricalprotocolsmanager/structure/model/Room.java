package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;

import java.util.List;

public class Room {

    private List<MeasurementMain> measurementMains;

    private String roomName;

    private String roomCascadeName;

    public Room(List<MeasurementMain> measurementMains, String roomName) {
        this.measurementMains = measurementMains;
        this.roomName = roomName;

        for(MeasurementMain measurementMain : measurementMains){
            setMeasurementMainCascadeName(this.roomCascadeName, measurementMain);
        }
    }

    public List<MeasurementMain> getMeasurementMains() {
        return measurementMains;
    }

    public void setMeasurementMains(List<MeasurementMain> measurementMains) {
        this.measurementMains = measurementMains;
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

    private void setMeasurementMainCascadeName(String roomCascadeName, MeasurementMain measurementMain) {
        measurementMain.setMeasurementMainCascadeName(roomCascadeName);
    }

    @Override
    public String toString() {
        return "Room{" +
                "measurementMains=" + measurementMains +
                ", roomName='" + roomName + '\'' +
                ", roomCascadeName='" + roomCascadeName + '\'' +
                '}';
    }
}
