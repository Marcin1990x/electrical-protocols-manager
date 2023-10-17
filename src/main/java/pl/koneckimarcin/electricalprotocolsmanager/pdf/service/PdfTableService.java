package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

@Service
public class PdfTableService {

    private final PdfTextService textService;

    public PdfTableService(PdfTextService textService) {
        this.textService = textService;
    }

    private final int maxTableSize = 500; // page area width

    public int[] calculateCellSizes(String nameForCalculation, int fontSize) throws IOException {

        int cellQuantity = calculateCellQuantity(nameForCalculation);
        int[] cellWidths = new int[cellQuantity];

        List<Integer> headersWidth;

        headersWidth = textService
                .calculateHeadersWidth(textService.getHeadersForCalculation(nameForCalculation), Font.font, fontSize);

        int totalHeadersWidth = headersWidth.stream().mapToInt(Integer::intValue).sum();
        float resolution = (float) maxTableSize / totalHeadersWidth;

        for (int i = 0; i < cellQuantity; i++) {
            cellWidths[i] = (int) (headersWidth.get(i) * resolution);
        }
        if(cellWidths.length == 0) { // check if error name is correct
            throw new InvalidObjectException("Empty cell widths array. Can't print the table.");
        }
        return cellWidths;
    }

    private int calculateCellQuantity(String measurementName) throws InvalidObjectException {

        int cellQuantity;

        if (measurementName.equals(TextData.measurementsMainNames.get(0))) { // case for each measurement main
            cellQuantity =  TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders.size();
        } else if (measurementName.equals(TextData.measurementsMainNames.get(1))){
            cellQuantity =  TextData.circuitInsulationResistanceTnsHeaders.size();
        } else if (measurementName.equals(TextData.measurementsMainNames.get(2))){
            cellQuantity =  TextData.circuitInsulationResistanceTncHeaders.size();
        } else if (measurementName.equals(TextData.measurementsMainNames.get(3))){
            cellQuantity =  TextData.residualCurrentProtectionHeaders.size();
        } else if (measurementName.equals(TextData.measurementsMainNames.get(4))){
            cellQuantity =  TextData.soilResistanceHeaders.size();
        }
        else if (measurementName.equals("ElectricianTable headers")) {
            cellQuantity =  TextData.electricianPdfTableHeaders.size();
        }
        else {
            // check if error name is correct
            throw new InvalidObjectException("Cell quantity for: " + measurementName + " is 0.");
        }
        return cellQuantity;
    }
}
