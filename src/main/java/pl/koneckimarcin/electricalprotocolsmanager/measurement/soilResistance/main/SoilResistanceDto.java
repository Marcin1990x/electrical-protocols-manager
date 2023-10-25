package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntryDto;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class SoilResistanceDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(4);

    @OneToMany
    private List<SoilResistanceEntryDto> entries;

    public SoilResistanceDto() {
    }

    public String getMeasurementName() {
        return measurementName;
    }
}