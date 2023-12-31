package pl.koneckimarcin.electricalprotocolsmanager.measurement.main;

import jakarta.persistence.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.room.Room;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "test", discriminatorType = DiscriminatorType.INTEGER)
public abstract class MeasurementMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
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

    ////////////
    public void setMeasurementEntries(List<MeasurementEntry> measurementEntries) {
        this.measurementEntries = measurementEntries;
    }

    public void addEntry(MeasurementEntry entry) {
        if (this.measurementEntries.size() < 37) {
            this.measurementEntries.add(entry);
        }
    }

    public void removeEntry(MeasurementEntry entry) {
        this.measurementEntries.remove(entry);
    }

    public void removeAllEntries() {
        this.measurementEntries.clear();
    }

    public List<Integer> listEntriesId() {

        List<Integer> entryIdList = new ArrayList<>();

        for (MeasurementEntry entry : this.measurementEntries) {
            entryIdList.add(entry.getId());
        }
        return entryIdList;
    }

    public void setMeasurementMainCascadeName(String roomCascadeName) {
        this.measurementMainCascadeName = roomCascadeName + "/" + this.measurementName;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public String getMeasurementMainCascadeNameWithoutMeasurementName() {

        String result = "";

        if (!this.measurementMainCascadeName.equals("")) {
            result = this.measurementMainCascadeName.replace(this.measurementName, "");
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public int[] getTableCellsSizes() {
        return new int[0];
    }

    public List<String> getMeasurementLegend() {
        return null;
    }

    public List<String> getMeasurementTheoryDirectory() {
        return null;
    }

    public List<Object> getMeasurementEntryTableHeaders() {
        return null;
    }
}
