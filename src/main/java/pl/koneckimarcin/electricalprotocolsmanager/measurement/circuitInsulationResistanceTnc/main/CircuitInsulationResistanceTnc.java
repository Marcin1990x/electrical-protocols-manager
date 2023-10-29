package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class CircuitInsulationResistanceTnc extends MeasurementMain {

    private final String measurementName = TextData.measurementsMainNames.get(2);

    private int uiso;

    @OneToMany
    private List<CircuitInsulationResistanceTncEntry> entries;

    @Positive(message = "This value is mandatory.")
    public int getUiso() {
        return uiso;
    }

    public void setUiso(int uiso) {
        this.uiso = uiso;
    }

    public List<CircuitInsulationResistanceTncEntry> getEntries() {
        return entries;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return TextData.circuitInsulationResistanceLabels.get(0) + " = " + this.uiso + " V ";
    }

    public String getMeasurementName() {
        return measurementName;
    }
}