package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

import java.util.ArrayList;
import java.util.List;

public class ProtectionAgainstElectricShockByAutomaticShutdown extends MeasurementMain {

    private final String measurementName = "Measurement Name 1";
    private int specificField1;
    private int specificField2;

    public ProtectionAgainstElectricShockByAutomaticShutdown(List<MeasurementEntry> measurementEntries,
                                                             int commonMainField1, int commonMainField2, int commonMainField3,
                                                             NetworkType networkType, int specificField1, int specificField2) {
        super(measurementEntries, commonMainField1, commonMainField2, commonMainField3, networkType);
        this.specificField1 = specificField1;
        this.specificField2 = specificField2;
    }

    public int getSpecificField1() {
        return specificField1;
    }

    public void setSpecificField1(int specificField1) {
        this.specificField1 = specificField1;
    }

    public int getSpecificField2() {
        return specificField2;
    }

    public void setSpecificField2(int specificField2) {
        this.specificField2 = specificField2;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    @Override
    public String getMeasurementMainCascadeName() {
        return super.getMeasurementMainCascadeName() + this.getMeasurementName();
    }

    @Override
    public List<String> getMeasurementsMainTextData() {

        List<String> measurementsTextData = new ArrayList<>();
        measurementsTextData.add(getMeasurementMainCascadeName());
        measurementsTextData.add("Specific main 1: " + getSpecificField1());
        measurementsTextData.add("Specific main 2: " + getSpecificField2());
        measurementsTextData.addAll(super.getMeasurementsMainTextData());

        return measurementsTextData;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return super.getPropertiesNamesAndValues() +
                ", SpecificField1 = " + this.specificField1 +
                ", SpecificField2 = " + this.specificField2;
    }

    @Override
    public String toString() {
        return "ProtectionAgainstElectricShockByAutomaticShutdown{" +
                "name='" + measurementName + '\'' +
                ", specificField1=" + specificField1 +
                ", specificField2=" + specificField2 +
                '}';
    }

    @Override
    public int[] getMeasurementStatistics() {

        int[] statistics = new int[2];
        statistics[0] = calculateMeasurePoints();
        statistics[1] = calculatePositiveResults();

        return statistics;
    }

    private int calculateMeasurePoints() {

        return this.getMeasurementEntries().size();
    }
    private int calculatePositiveResults() {

        return (int)this.getMeasurementEntries().stream().filter(entry -> entry.getResult() == Result.POSITIVE).count();
    }
}
