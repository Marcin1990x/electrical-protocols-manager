package pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.main;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementSpecificData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.entry.ResidualCurrentProtectionParametersEntry;

import java.util.List;

@Entity
@DiscriminatorValue("3")
public class ResidualCurrentProtectionParameters extends MeasurementMain implements MeasurementSpecificData {

    private final String measurementName = TextsPL.measurementsMainNames.get(3);

    @OneToMany
    private List<ResidualCurrentProtectionParametersEntry> entries;

    public ResidualCurrentProtectionParameters() {
    }
    public String getMeasurementName() {
        return measurementName;
    }

    @Override
    public int[] getTableCellsSizes() {
        return new int[]{20, 50, 80, 55, 30, 30, 30, 40, 40, 30, 30, 65};
    }

    @Override
    public List<String> getMeasurementLegend() {
        return TextsPL.residualCurrentProtectionLegendText;
    }
    @Override
    public List<String> getMeasureTheoryDirectory() {
        return List.of("theoryImages/residual.jpg");
    }
}