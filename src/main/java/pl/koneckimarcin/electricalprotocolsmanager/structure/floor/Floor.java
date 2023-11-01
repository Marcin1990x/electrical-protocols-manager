package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;

import java.util.List;

@Entity
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String floorCascadeName;

    private String floorName;

    @ManyToOne
    private Building building;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Room> rooms;

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getId() {
        return id;
    }

    public String getFloorName() {
        return floorName;
    }

    public String getFloorCascadeName() {
        return floorCascadeName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setFloorCascadeName(String buildingName) {
        this.floorCascadeName = buildingName + "/" + this.floorName;
    }
    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setRoomCascadeName(this.floorCascadeName);
    }
    public void removeRoom(Room room) {
        this.rooms.remove(room);
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
}
