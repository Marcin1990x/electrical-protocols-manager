package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class CircuitInsulationResistanceTncDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(2);

    private int uiso;

    @OneToMany
    private List<CircuitInsulationResistanceTncEntryDto> entries;

    public int getUiso() {
        return uiso;
    }

    public void setUiso(int uiso) {
        this.uiso = uiso;
    }

    public List<CircuitInsulationResistanceTncEntryDto> getEntries() {
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