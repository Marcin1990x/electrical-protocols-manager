package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntry;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class SoilResistance extends MeasurementMain {

    private final String measurementName = TextsPL.measurementsMainNames.get(4);

    @OneToMany
    private List<SoilResistanceEntry> entries;

    public SoilResistance() {
    }

    public String getMeasurementName() {
        return measurementName;
    }
}