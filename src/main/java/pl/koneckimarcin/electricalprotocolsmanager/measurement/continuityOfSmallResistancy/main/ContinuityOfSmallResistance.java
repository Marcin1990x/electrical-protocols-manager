package pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.entry.ContinuityOfSmallResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;

import java.util.List;

@Entity
@DiscriminatorValue("5")
public class ContinuityOfSmallResistance extends MeasurementMain {

    private final String measurementName = TextsPL.measurementsMainNames.get(5);

    @OneToMany
    private List<ContinuityOfSmallResistanceEntry> entries;

    public ContinuityOfSmallResistance() {
    }

    public List<ContinuityOfSmallResistanceEntry> getEntries() {
        return entries;
    }

    public String getMeasurementName() {
        return measurementName;
    }
}