package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementSpecificData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

@Entity
@DiscriminatorValue("1")
public class CircuitInsulationResistanceTns extends MeasurementMain implements MeasurementSpecificData {

    private final String measurementName = TextsPL.measurementsMainNames.get(1);

    private int uiso;

    @OneToMany
    private List<CircuitInsulationResistanceTnsEntry> entries;

    @Positive(message = "This value is mandatory.")
    public int getUiso() {
        return uiso;
    }

    public void setUiso(int uiso) {
        this.uiso = uiso;
    }

    public List<CircuitInsulationResistanceTnsEntry> getEntries() {
        return entries;
    }

    @Override
    public String getPropertiesNamesAndValues() {
        return TextsPL.circuitInsulationResistanceLabels.get(0) + " = " + this.uiso + " V ";
    }

    public String getMeasurementName() {
        return measurementName;
    }


    @Override
    public int[] getTableCellsSizes() {
        return new int[]{20, 35, 65, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 50};
    }
}