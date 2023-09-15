package pl.koneckimarcin.electricalprotocolsmanager.structure;

import java.util.List;

public class Floor {

    private List<Room> rooms;

    private String name;

    public Floor(List<Room> rooms, String name) {
        this.rooms = rooms;
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "rooms=" + rooms +
                ", name='" + name + '\'' +
                '}';
    }
}
