package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;

import java.util.List;

@Service
public class PdfService {

    public int calculateNumberOfPagesForMeasurements(List<Floor> floors) {

        int count = 0;

        for (Floor floor : floors) {
            count += floor.calculateMeasurementMainQuantity();
        }
        return count;
    }

    public int calculateNumberOfTheoryPages(Building building) {

        int count = 0;
        boolean circuitFlag = false;

        List<String> distinctNames = building.extractMeasurementMainDistinctNames();

        for (String name : distinctNames) {
            if (name.equals(TextsPL.measurementsMainNames.get(0))) {
                count += 2;
            } else if (name.equals(TextsPL.measurementsMainNames.get(1)) ||
                    name.equals(TextsPL.measurementsMainNames.get(2))
            ) {
                if (!circuitFlag) count++;
                circuitFlag = true;
            } else if (name.equals(TextsPL.measurementsMainNames.get(3))) {
                count++;
            } else if (name.equals(TextsPL.measurementsMainNames.get(5))) {
                count++;
            } else if (name.equals(TextsPL.measurementsMainNames.get(4))) {
            } else {
                throw new IllegalArgumentException("No theory pages for this measurement name.");
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
