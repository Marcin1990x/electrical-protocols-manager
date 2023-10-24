package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;


public class ResidualCurrentProtectionParameters extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(3);

    public ResidualCurrentProtectionParameters(){}

    public String getMeasurementName() {
        return measurementName;
    }
}