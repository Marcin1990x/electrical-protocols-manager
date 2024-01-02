package pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementSpecificData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class CircuitInsulationResistanceTnc extends MeasurementMain implements MeasurementSpecificData {

    private final String measurementName = TextsPL.measurementsMainNames.get(2);

    private int uiso;

    @OneToMany
    private List<CircuitInsulationResistanceTncEntry> entries;

    @Positive(message = "This value is mandatory.")
    public int getUiso() {
        return uiso;
    }

    public void setUiso(int uiso) {
        this.uiso = uiso;
    }

    public List<CircuitInsulationResistanceTncEntry> getEntries() {
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
        return new int[]{20, 55, 85, 40, 39, 39, 39, 39, 39, 40, 65};
    }

    @Override
    public List<String> getMeasurementLegend() {
        return TextsPL.circuitInsulationResistanceTncLegendText;
    }

    @Override
    public List<String> getMeasurementTheoryDirectory() {
        return List.of("theoryImages/insulation.jpg");
    }

    @Override
    public List<Object> getMeasurementEntryTableHeaders() {

        return TextsPL.circuitInsulationResistanceTncHeaders;
    }

}