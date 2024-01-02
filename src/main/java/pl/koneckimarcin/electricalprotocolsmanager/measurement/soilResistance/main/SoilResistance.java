package pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementSpecificData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.entry.SoilResistanceEntry;

import java.util.List;

@Entity
@DiscriminatorValue("4")
public class SoilResistance extends MeasurementMain implements MeasurementSpecificData {

    private final String measurementName = TextsPL.measurementsMainNames.get(4);

    @OneToMany
    private List<SoilResistanceEntry> entries;

    public SoilResistance() {
    }

    public String getMeasurementName() {
        return measurementName;
    }

    @Override
    public int[] getTableCellsSizes() {
        return new int[]{20, 50, 100, 110, 110, 110};
    }

    @Override
    public List<String> getMeasurementLegend() {
        return TextsPL.soilResistanceLegendText;
    }
    @Override
    public List<Object> getMeasurementEntryTableHeaders() {

        return TextsPL.soilResistanceHeaders;
    }
}