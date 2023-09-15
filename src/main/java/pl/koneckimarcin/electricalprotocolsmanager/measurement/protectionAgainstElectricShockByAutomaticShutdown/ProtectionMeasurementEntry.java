package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;

public class ProtectionMeasurementEntry extends MeasurementEntry {

    private int specificField1;
    private int specificField2;

    private char specificField3;

    public ProtectionMeasurementEntry(int commonField1, int commonField2, int commonField3,
                                      String commonField4, Result result, int specificField1,
                                      int specificField2, char specificField3) {
        super(commonField1, commonField2, commonField3, commonField4, result);
        this.specificField1 = specificField1;
        this.specificField2 = specificField2;
        this.specificField3 = specificField3;
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

    public char getSpecificField3() {
        return specificField3;
    }

    public void setSpecificField3(char specificField3) {
        this.specificField3 = specificField3;
    }

    @Override
    public String toString() {
        return "ProtectionMeasurementEntry{" +
                "specificField1=" + specificField1 +
                ", specificField2=" + specificField2 +
                ", specificField3=" + specificField3 +
                '}';
    }
}
