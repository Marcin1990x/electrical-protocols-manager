package pl.koneckimarcin.electricalprotocolsmanager.structure.model;

import jakarta.persistence.*;

@Entity
public class RoomDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roomCascadeName;

    private String roomName;

    @ManyToOne
    private FloorDto floorDto;

    public int getId() {
        return id;
    }

    public String getRoomCascadeName() {
        return roomCascadeName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomCascadeName(String floorCascadeName) {
        this.roomCascadeName = floorCascadeName + "/" + this.roomName;
    }
}
