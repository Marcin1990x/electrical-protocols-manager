package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.entry.CircuitInsulationResistanceTnsEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;

import java.util.List;

@Entity
@DiscriminatorValue("1")
public class CircuitInsulationResistanceTnsDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(1);

    private int uiso;

    @OneToMany
    private List<CircuitInsulationResistanceTnsEntryDto> entries;

    public int getUiso() {
        return uiso;
    }

    public void setUiso(int uiso) {
        this.uiso = uiso;
    }

    public List<CircuitInsulationResistanceTnsEntryDto> getEntries() {
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