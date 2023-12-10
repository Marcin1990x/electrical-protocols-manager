package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntry;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class ResidualCurrentProtectionParameters extends MeasurementMain {

    private final String measurementName = TextsPL.measurementsMainNames.get(3);

    @OneToMany
    private List<ResidualCurrentProtectionParametersEntry> entries;

    public ResidualCurrentProtectionParameters() {
    }
    public String getMeasurementName() {
        return measurementName;
    }
}