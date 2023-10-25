package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMainDto;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntryDto;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class ResidualCurrentProtectionParametersDto extends MeasurementMainDto {

    private final String measurementName = TextData.measurementsMainNames.get(3);

    @OneToMany
    private List<ResidualCurrentProtectionParametersEntryDto> entries;

    public ResidualCurrentProtectionParametersDto() {
    }
    public String getMeasurementName() {
        return measurementName;
    }
}