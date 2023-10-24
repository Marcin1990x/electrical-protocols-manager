package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;


public class CircuitInsulationResistanceTnc extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(2);

    private int uiso;

    public CircuitInsulationResistanceTnc(int uiso) {
        this.uiso = uiso;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return TextData.circuitInsulationResistanceLabels.get(0) + " = " + this.uiso + " V ";
    }

    public String getMeasurementName() {
        return measurementName;
    }
}