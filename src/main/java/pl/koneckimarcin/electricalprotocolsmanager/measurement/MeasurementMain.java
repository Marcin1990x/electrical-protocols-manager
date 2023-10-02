package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import java.util.ArrayList;
import java.util.List;

public abstract class MeasurementMain {

    private List<MeasurementEntry> measurementEntries;

    private String measurementName = "";
    private String measurementMainCascadeName;

    private int commonMainField1;
    private int commonMainField2;
    private int commonMainField3;

    private NetworkType networkType;

    public MeasurementMain(List<MeasurementEntry> measurementEntries, int commonMainField1,
                           int commonMainField2, int commonMainField3, NetworkType networkType) {
        this.measurementEntries = measurementEntries;
        this.commonMainField1 = commonMainField1;
        this.commonMainField2 = commonMainField2;
        this.commonMainField3 = commonMainField3;
        this.networkType = networkType;
    }

    public List<MeasurementEntry> getMeasurementEntries() {
        return measurementEntries;
    }

    public void setMeasurementEntries(List<MeasurementEntry> measurementEntries) {
        this.measurementEntries = measurementEntries;
    }

    public int getCommonMainField1() {
        return commonMainField1;
    }

    public void setCommonMainField1(int commonMainField1) {
        this.commonMainField1 = commonMainField1;
    }

    public int getCommonMainField2() {
        return commonMainField2;
    }

    public void setCommonMainField2(int commonMainField2) {
        this.commonMainField2 = commonMainField2;
    }

    public int getCommonMainField3() {
        return commonMainField3;
    }

    public void setCommonMainField3(int commonMainField3) {
        this.commonMainField3 = commonMainField3;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public void setMeasurementMainCascadeName(String roomCascadeName) {
        this.measurementMainCascadeName = roomCascadeName + "/" + this.measurementName;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public String getMeasurementMainCascadeName() {
        return measurementMainCascadeName;
    }

    public String getMeasurementMainCascadeNameWithoutMeasurementName() {

        String result = this.measurementMainCascadeName.replace(this.measurementName, "");
        return result.substring(0 , result.length()-1);
    }

    public List<String> getMeasurementsMainTextData() {

        List<String> measurementsTextData = new ArrayList<>();
        measurementsTextData.add("Common main 1: " + getCommonMainField1());
        measurementsTextData.add("Common main 2: " + getCommonMainField2());
        measurementsTextData.add("Common main 3: " + getCommonMainField3());
        measurementsTextData.add("Network type: " + getNetworkType().toString());

        for(MeasurementEntry entry : getMeasurementEntries()){
            measurementsTextData.addAll(entry.getMeasurementEntriesTextData());
        }
        return measurementsTextData;
    }

    public String getPropertiesNamesAndValues() {
        return "CommonMainField1 = " + this.commonMainField1 +
                ", CommonMainField2 = " + this.commonMainField2 +
                ", CommonMainField3 = " + this.commonMainField3 +
                ", NetworkType = " + this.networkType.name();
    }
    @Override
    public String toString() {
        return "MeasurementMain{" +
                "measurementEntries=" + measurementEntries +
                ", commonMainField1=" + commonMainField1 +
                ", commonMainField2=" + commonMainField2 +
                ", commonMainField3=" + commonMainField3 +
                ", networkType=" + networkType +
                '}';
    }

    public int[] getMeasurementStatistics() {

        int[] statistics = new int[2];

        return statistics;
    }
}
