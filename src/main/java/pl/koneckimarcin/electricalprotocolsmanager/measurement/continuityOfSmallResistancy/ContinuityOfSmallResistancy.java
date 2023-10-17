package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;


public class ContinuityOfSmallResistancy extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(5);

    public ContinuityOfSmallResistancy() {};

    public String getMeasurementName() {
        return measurementName;
    }
}