package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "test", discriminatorType = DiscriminatorType.INTEGER)
public abstract class MeasurementMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MeasurementEntry> measurementEntries;

    @ManyToOne
    private Room room;

    private String measurementName = "";
    private String measurementMainCascadeName = "";

    public int getId() {
        return id;
    }

    public String getPropertiesNamesAndValues() {
        return "";
    }

    public List<MeasurementEntry> getMeasurementEntries() {
        return measurementEntries;
    }

    public void addEntry(MeasurementEntry entry) {
        this.measurementEntries.add(entry);
    }

    public void setMeasurementMainCascadeName(String roomCascadeName) {
        this.measurementMainCascadeName = roomCascadeName + "/" + this.measurementName;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public String getMeasurementMainCascadeNameWithoutMeasurementName() {

        String result = "";

        if(!this.measurementMainCascadeName.equals("")) {
            result = this.measurementMainCascadeName.replace(this.measurementName, "");
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
