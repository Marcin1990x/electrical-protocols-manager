package pl.koneckimarcin.electricalprotocolsmanager.structure.room;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roomCascadeName;

    private String roomName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MeasurementMain> measurementMains;

    @ManyToOne
    private Floor floorDto;

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

    public List<MeasurementMain> getMeasurementMains() {
        return measurementMains;
    }

    public boolean addMeasurementMain(MeasurementMain measurementMain) {

        if(!checkForDuplicates(measurementMain)) {

            this.measurementMains.add(measurementMain);
            measurementMain.setMeasurementMainCascadeName(this.roomCascadeName);
            return true;
        }
        return false;
    }
    public void removeMeasurementMain(MeasurementMain mainToDelete) {
        this.measurementMains.remove(mainToDelete);
    }

    public void setRoomCascadeName(String floorCascadeName) {
        this.roomCascadeName = floorCascadeName + "/" + this.roomName;
    }

    private boolean checkForDuplicates(MeasurementMain main) {

        boolean isDuplicate = false;

        for(MeasurementMain measurementMain : this.measurementMains){
            if(measurementMain.getMeasurementName().equals(main.getMeasurementName())){
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }
}
