package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

import java.util.List;

public class MeasurementLegend {

    private String measurementName;
    private List<String> legendText;

    public MeasurementLegend(String measurementName) {
        this.measurementName = measurementName;
        this.legendText = addLegend();
    }

    private List<String> addLegend() {

        List<String> legend;

        if (isTextEqual(TextsPL.measurementsMainNames.get(0))) {
            legend = TextsPL.protectionAgainstElectricShockByAutomaticShutdownLegendText;
        } else if (isTextEqual(TextsPL.measurementsMainNames.get(1))) {
            legend = TextsPL.circuitInsulationResistanceTnsLegendText;
        } else if (isTextEqual(TextsPL.measurementsMainNames.get(2))) {
            legend = TextsPL.circuitInsulationResistanceTncLegendText;
        } else if (isTextEqual(TextsPL.measurementsMainNames.get(3))) {
            legend = TextsPL.residualCurrentProtectionLegendText;
        } else if (isTextEqual(TextsPL.measurementsMainNames.get(4))) {
            legend = TextsPL.soilResistanceLegendText;
        } else if (isTextEqual(TextsPL.measurementsMainNames.get(5))) {
            legend = TextsPL.continuityOfSmallResistanceLegendText;
        } else {
            throw new IllegalArgumentException("No legend text for this measurement main name.");
        }
        return legend;
    }

    private boolean isTextEqual(String text) {
        return this.measurementName.equals(text);
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public List<String> getLegendText() {
        return legendText;
    }
}
