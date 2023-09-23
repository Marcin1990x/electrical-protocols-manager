package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;

import java.util.List;

public class ProtectionAgainstElectricShockByAutomaticShutdown extends MeasurementMain {

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

    @Override
    public List<String> getMeasurementsMainTextData() {

        List<String> measurementsTextData = super.getMeasurementsMainTextData();
        measurementsTextData.add("Specific main 1: " + getSpecificField1());
        measurementsTextData.add("Specific main 2: " + getSpecificField2());

        return measurementsTextData;
    }

    @Override
    public String toString() {
        return "ProtectionAgainstElectricShockByAutomaticShutdown{" +
                "specificField1=" + specificField1 +
                ", specificField2=" + specificField2 +
                '}';
    }
}
