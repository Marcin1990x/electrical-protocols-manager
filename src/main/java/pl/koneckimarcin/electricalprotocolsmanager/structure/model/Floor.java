package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private List<Room> rooms = new ArrayList<>();

    private String floorName;

    private String floorCascadeName;

    public Floor(String floorName) {
        this.floorName = floorName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setRoomCascadeName(this.floorCascadeName);
    }

    public String getBuildingName() {
        return floorName;
    }

    public void setBuildingName(String buildingName) {
        this.floorName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorCascadeName() {
        return floorCascadeName;
    }

    public int calculateMainMeasurementsCount() {

        int count = 0;

        //check if one page is enough

        for (Room room : getRooms()) {
            if (room.getMeasurementMains().size() > 0) {
                count += room.getMeasurementMains().size(); // every measurement on new page
            }
        }
        return count;
    }

    public void setFloorCascadeName(String buildingName) {
        this.floorCascadeName = buildingName + "/" + this.floorName;
    }

    private void setRoomCascadeName(String floorCascadeName, Room room) {
        room.setRoomCascadeName(floorName);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "rooms=" + rooms +
                ", floorName='" + floorName + '\'' +
                ", floorCascadeName='" + floorCascadeName + '\'' +
                '}';
    }
}
