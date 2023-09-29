package pl.koneckimarcin.electricalprotocolsmanager.measurement;

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

        if (this.measurementName.equals("Measurement Name 1")) {
            legend = List.of("Legend1", "Legend2", "Legend3", "Legend4");
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
