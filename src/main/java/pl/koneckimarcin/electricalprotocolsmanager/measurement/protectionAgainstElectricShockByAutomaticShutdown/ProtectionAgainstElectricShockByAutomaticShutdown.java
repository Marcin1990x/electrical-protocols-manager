package pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

public class ProtectionAgainstElectricShockByAutomaticShutdown extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(0);

    //increment id

    public ProtectionAgainstElectricShockByAutomaticShutdown(int un, int ui, float ko, float ta, NetworkType networkType) {
        super(un, ui, ko, ta, networkType);
    }

    public String getMeasurementName() {
        return measurementName;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return super.getPropertiesNamesAndValues();
    }

    @Override
    public String toString() {
        return "ProtectionAgainstElectricShockByAutomaticShutdown{" +
                "measurementName='" + measurementName + '\'' +
                '}';
    }
}
