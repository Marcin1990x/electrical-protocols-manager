package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.io.InvalidObjectException;

@Service
public class PdfTableService {

    private final PdfTextService textService;

    public PdfTableService(PdfTextService textService) {
        this.textService = textService;
    }

    public int[] getCellSizes(String measurementName) throws InvalidObjectException {

        int[] cellSizes;

        if (measurementName.equals(TextData.measurementsMainNames.get(0))) {
            cellSizes = new int[]{20, 55, 85, 55, 30, 35, 35, 40, 40, 40, 65};
        } else if (measurementName.equals(TextData.measurementsMainNames.get(1))) {
            cellSizes = new int[]{20, 40, 70, 30, 30, 30, 30, 30, 30, 30, 30, 25, 25, 25, 55};
        } else if (measurementName.equals(TextData.measurementsMainNames.get(2))) {
            cellSizes = new int[]{20, 55, 85, 40, 39, 39, 39, 39, 39, 40, 65};
        } else if (measurementName.equals(TextData.measurementsMainNames.get(3))) {
            cellSizes = new int[]{20, 50, 80, 55, 30, 30, 30, 40, 40, 30, 30, 65};
        } else if (
                measurementName.equals(TextData.measurementsMainNames.get(4))
                || measurementName.equals(TextData.measurementsMainNames.get(5))
        ) {
            cellSizes = new int[]{20, 50, 100, 110, 110, 110};
        } else {
            // check if error name is correct
            throw new InvalidObjectException("No cell sizes prepared for: " + measurementName);
        }
        return cellSizes;
    }
}
