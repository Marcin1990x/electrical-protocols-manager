package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;


public class SoilResistance extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(4);

    public SoilResistance(){}

    public String getMeasurementName() {
        return measurementName;
    }
}