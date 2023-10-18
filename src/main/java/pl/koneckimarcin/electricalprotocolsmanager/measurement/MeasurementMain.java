package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import java.io.Serializable;
import java.util.List;

public abstract class MeasurementMain {

    private List<MeasurementEntry> measurementEntries;

    private String measurementName = "";
    private String measurementMainCascadeName;

    public MeasurementMain() {
    }

    public String getPropertiesNamesAndValues() {
        return "";
    }

    public List<MeasurementEntry> getMeasurementEntries() {
        return measurementEntries;
    }

    public void setMeasurementEntries(List<MeasurementEntry> measurementEntries) {
        this.measurementEntries = measurementEntries;
    }

    public void setMeasurementMainCascadeName(String roomCascadeName) {
        this.measurementMainCascadeName = roomCascadeName + "/" + this.measurementName;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public String getMeasurementMainCascadeNameWithoutMeasurementName() {

        String result = this.measurementMainCascadeName.replace(this.measurementName, "");
        return result.substring(0, result.length() - 1);
    }
}
