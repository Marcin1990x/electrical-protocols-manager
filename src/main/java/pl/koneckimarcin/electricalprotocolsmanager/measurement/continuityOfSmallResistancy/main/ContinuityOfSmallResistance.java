package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;


public class ContinuityOfSmallResistance extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(5);

    public ContinuityOfSmallResistance() {};

    public String getMeasurementName() {
        return measurementName;
    }
}