package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.util.List;

public abstract class MeasurementMain {

    private List<MeasurementEntry> measurementEntries;

    private String measurementName = "";
    private String measurementMainCascadeName;

    private int un;
    private int ui;
    private float ko;
    private float ta;

    private NetworkType networkType;

    public MeasurementMain(int un, int ui, float ko, float ta, NetworkType networkType) {
        this.un = un;
        this.ui = ui;
        this.ko = ko;
        this.ta = ta;
        this.networkType = networkType;
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

    public String getPropertiesNamesAndValues() {
        return TextData.measurementMainLabels.get(0) + " = " + this.un + " V " +
                TextData.measurementMainLabels.get(1) + " = " + this.ui + " V " +
                TextData.measurementMainLabels.get(2) + " = " + this.ko +
                TextData.measurementMainLabels.get(3) + " = " + this.ta + " s " +
                TextData.measurementMainLabels.get(4) + " = " + this.networkType.name();
    }

    @Override
    public String toString() {
        return "MeasurementMain{" +
                "measurementEntries=" + measurementEntries +
                ", measurementName='" + measurementName + '\'' +
                ", measurementMainCascadeName='" + measurementMainCascadeName + '\'' +
                ", un=" + un +
                ", ui=" + ui +
                ", ko=" + ko +
                ", ta=" + ta +
                ", networkType=" + networkType +
                '}';
    }
}
