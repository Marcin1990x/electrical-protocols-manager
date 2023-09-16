package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.util.ArrayList;
import java.util.List;

public class PdfMeasurementsData {

    private List<String> measurementData;

    public void setMeasurementData(Building building) {

        List<String> lines = new ArrayList<>();

        lines.add(building.getName());

        building.getFloors()
                .forEach(floor -> {
                    lines.add(floor.getName());
                    floor.getRooms()
                            .forEach(room -> {
                                lines.add(room.getName());
                                room.getMeasurementMains()
                                        .forEach(measurementMain -> {
                                            lines.add(measurementMain.toString());
                                            measurementMain.getMeasurementEntries()
                                                    .forEach(entry -> {
                                                        lines.add(entry.toString());
                                                    });
                                        });
                            });

                });
        this.measurementData = lines;
    }

    public List<String> getMeasurementData() {
        return measurementData;
    }
}
