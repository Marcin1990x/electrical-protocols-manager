package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.main;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

public class ProtectionAgainstElectricShockByAutomaticShutdown extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(0);

    private int un;
    private int ui;
    private float ko;
    private float ta;
    private NetworkType networkType;

    public ProtectionAgainstElectricShockByAutomaticShutdown(int un, int ui, float ko, float ta, NetworkType networkType) {
        this.un = un;
        this.ui = ui;
        this.ko = ko;
        this.ta = ta;
        this.networkType = networkType;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(0) + " = " + this.un + " V " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(1) + " = " + this.ui + " V " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(2) + " = " + this.ko + " " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(3) + " = " + this.ta + "s " +
                TextData.protectionAgainstElectricShockByAutomaticShutdownLabels.get(4) + " = " + this.networkType.name();
    }

    public String getMeasurementName() {
        return measurementName;
    }
}
