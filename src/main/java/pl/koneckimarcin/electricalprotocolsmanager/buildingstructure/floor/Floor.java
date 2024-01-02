package pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.floor;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.Room;

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

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
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

    public int calculateMeasurementMainQuantity() {

        int count = 0;

        for (Room room : getRooms()) {
            if (isNotEmpty(room.getMeasurementMains())) {
                count += room.getMeasurementMains().size();
            }
        }
        return count;
    }
    private boolean isNotEmpty(List<MeasurementMain> list) {
        return list.size() > 0;
    }
}
