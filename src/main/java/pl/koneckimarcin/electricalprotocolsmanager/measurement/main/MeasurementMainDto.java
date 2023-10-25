package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.RoomDto;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "test", discriminatorType = DiscriminatorType.INTEGER)
public abstract class MeasurementMainDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<MeasurementEntryDto> measurementEntries;

    @ManyToOne
    private RoomDto room;

    private String measurementName = "";
    private String measurementMainCascadeName = "";

    public int getId() {
        return id;
    }

    public String getPropertiesNamesAndValues() {
        return "";
    }

    public List<MeasurementEntryDto> getMeasurementEntries() {
        return measurementEntries;
    }

    public void addEntry(MeasurementEntryDto entry) {
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
