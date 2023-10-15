package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;

import java.util.List;

@Service
public class PdfService {

    public int calculateNumberOfMeasurementsPages(Building building) {

        int count = 0;

        for (Floor floor : building.getFloors()) {
            count += floor.calculateMainMeasurementsCount();
        }
        return count;
    }

    public int calculateNumberOfTheoryPages(Building building) {

        int count = 0;

        List<String> distinctNames = building.extractMeasurementMainDistrictNames();

        for(String name : distinctNames) {
            if(name.equals(TextData.measurementsMainNames.get(0))) {
                count += 2;
            } else if(name.equals(TextData.measurementsMainNames.get(1))) {
                count++;
            } else {
                throw new IllegalArgumentException("No theory pages for this measuremement name.");
            }
        }
        return count;
    }
    public void addPages(PDDocument document, int pages) {

        for (int i = 0; i < pages; i++) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
        }
    }
}
