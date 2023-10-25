package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntryDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;

import java.util.List;

@Entity
@DiscriminatorValue("5")
public class ContinuityOfSmallResistanceDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(5);

    @OneToMany
    private List<ContinuityOfSmallResistanceEntryDto> entries;

    public ContinuityOfSmallResistanceDto() {
    }

    public List<ContinuityOfSmallResistanceEntryDto> getEntries() {
        return entries;
    }

    public String getMeasurementName() {
        return measurementName;
    }
}