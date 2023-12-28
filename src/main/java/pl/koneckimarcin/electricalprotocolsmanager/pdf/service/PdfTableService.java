package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;

import java.io.InvalidObjectException;

@Service
public class PdfTableService {

    private final PdfTextService textService;

    public PdfTableService(PdfTextService textService) {
        this.textService = textService;
    }

    public int[] getCellSizes(String measurementName) throws IllegalArgumentException {

        int[] cellSizes;

        if (measurementName.equals(TextsPL.measurementsMainNames.get(0))) {
            cellSizes = new int[]{20, 55, 85, 55, 30, 35, 35, 40, 40, 40, 65};
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(1))) {
            cellSizes = new int[]{20, 35, 65, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 50};
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(2))) {
            cellSizes = new int[]{20, 55, 85, 40, 39, 39, 39, 39, 39, 40, 65};
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(3))) {
            cellSizes = new int[]{20, 50, 80, 55, 30, 30, 30, 40, 40, 30, 30, 65};
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(4))) {
            cellSizes = new int[]{20, 50, 100, 110, 110, 110};
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(5))) {
            cellSizes = new int[]{20, 50, 100, 105, 80, 80, 65};
        } else {
            // check if error name is correct
            throw new IllegalArgumentException("No cell sizes prepared for: " + measurementName);
        }
        return cellSizes;
    }
}
