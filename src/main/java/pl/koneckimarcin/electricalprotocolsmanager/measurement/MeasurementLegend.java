package pl.koneckimarcin.electricalprotocolsmanager.measurement;

import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.util.ArrayList;
import java.util.List;

public class MeasurementLegend {

    private String measurementName;
    private List<String> legendText;

    public MeasurementLegend(String measurementName) {
        this.measurementName = measurementName;
        this.legendText = addLegend();
    }

    private List<String> addLegend() {

        List<String> legend = new ArrayList<>();

        if (this.measurementName.equals(TextData.measurementsMainNames.get(0))) {
            legend = TextData.protectionAgainstElectricShockByAutomaticShutdownLegendText;
        }
        return legend;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public List<String> getLegendText() {
        return legendText;
    }
}
