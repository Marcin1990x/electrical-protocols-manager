package pl.koneckimarcin.electricalprotocolsmanager.structure.floor;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto;

import java.util.List;

@Entity
public class FloorDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String floorCascadeName;

    private String floorName;

    @ManyToOne
    private BuildingDto buildingDto;

    @OneToMany
    private List<RoomDto> rooms;

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

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setFloorCascadeName(String buildingName) {
        this.floorCascadeName = buildingName + "/" + this.floorName;
    }
    public void addRoom(RoomDto room) {
        this.rooms.add(room);
        room.setRoomCascadeName(this.floorCascadeName);
    }
    public int calculateMainMeasurementsCount() {

        int count = 0;

        //check if one page is enough

        for (RoomDto room : getRooms()) {
            if (room.getMeasurementMains().size() > 0) {
                count += room.getMeasurementMains().size(); // every measurement on new page
            }
        }
        return count;
    }
}
