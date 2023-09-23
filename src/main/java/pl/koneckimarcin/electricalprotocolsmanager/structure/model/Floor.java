package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import java.util.List;

public class Floor {

    private List<Room> rooms;

    private String floorName;

    private String floorCascadeName;

    public Floor(List<Room> rooms, String floorName) {
        this.rooms = rooms;
        this.floorName = floorName;

        for(Room room : rooms){
            setRoomCascadeName(this.floorName, room);
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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

    public void setFloorCascadeName(String floorName, Room room) {

        room.setRoomCascadeName(floorName);
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

    public void setFloorCascadeName(String buildingName){
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
