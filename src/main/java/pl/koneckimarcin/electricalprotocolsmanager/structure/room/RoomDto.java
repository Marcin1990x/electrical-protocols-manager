package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.FloorDto;

import java.util.List;

@Entity
public class RoomDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roomCascadeName;

    private String roomName;

    @OneToMany
    private List<MeasurementMainDto> measurementMains;

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

    public List<MeasurementMainDto> getMeasurementMains() {
        return measurementMains;
    }

    public void addMeasurementMain(MeasurementMainDto measurementMain) {
        this.measurementMains.add(measurementMain);
        measurementMain.setMeasurementMainCascadeName(this.roomCascadeName);
    }

    public void setRoomCascadeName(String floorCascadeName) {
        this.roomCascadeName = floorCascadeName + "/" + this.roomName;
    }
}
