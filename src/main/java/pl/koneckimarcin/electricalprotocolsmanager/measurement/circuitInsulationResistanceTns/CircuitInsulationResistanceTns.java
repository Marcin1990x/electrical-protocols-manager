package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;


public class CircuitInsulationResistanceTns extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(1);

    private int uiso;

    public CircuitInsulationResistanceTns(int uiso) {
        this.uiso = uiso;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return TextData.circuitInsulationResistanceTnsLabels.get(0) + " = " + this.uiso + " V ";
    }

    @Override
    public String getMeasurementName() {
        return measurementName;
    }
}