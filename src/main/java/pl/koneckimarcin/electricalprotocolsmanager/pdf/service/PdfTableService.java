package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.List;

@Service
public class PdfTableService {

    private final PdfTextService textService;

    public PdfTableService(PdfTextService textService) {
        this.textService = textService;
    }

    private final int maxTableSize = 500; // page area width

    public int[] getCellSizes(String nameForCalculation, int fontSize) throws IOException {

        int cellQuantity = calculateCellQuantity(nameForCalculation);

        List<Integer> headersWidth;

        headersWidth = textService
                .calculateHeadersWidth(textService.getHeadersForCalculation(nameForCalculation), Font.font, fontSize);

        int totalHeadersWidth = headersWidth.stream().mapToInt(Integer::intValue).sum();
        float resolution = (float) maxTableSize / totalHeadersWidth;

        int[] cellWidths = calculateCellSizes(cellQuantity, headersWidth, resolution);

        if(cellWidths.length == 0) { // check if error name is correct
            throw new InvalidObjectException("Empty cell widths array. Can't print the table.");
        }
        return cellWidths;
    }

    private int[] calculateCellSizes(int cellQuantity, List<Integer> headersWidth, float resolution) {

        int[] cellWidths = new int[cellQuantity];

        for (int i = 0; i < cellQuantity; i++) {
            cellWidths[i] = (int) (headersWidth.get(i) * resolution);
            if(cellWidths[i] < 20) cellWidths[i] = 20; // minimum cell width
        }
        if(Arrays.stream(cellWidths).sum() != 500) {
            cellWidths = correctCellSizes(cellWidths);
        }
        return cellWidths;
    }
    private int[] correctCellSizes(int[] cellWidths) {

        int cellsSizeTotal = Arrays.stream(cellWidths).sum();

        int i = 0;

        if(cellsSizeTotal > 500) {
            while(cellsSizeTotal != 500) {
                cellWidths[i] -= 1;
                i++;
                cellsSizeTotal--;
                if(i == cellWidths.length) i = 0;
            }
        } else if(cellsSizeTotal < 500) {
            while(cellsSizeTotal != 500) {
                cellWidths[i] += 1;
                i++;
                cellsSizeTotal++;
                if(i == cellWidths.length) i = 0;
            }
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
        } else if (measurementName.equals(TextData.measurementsMainNames.get(5))){
            cellQuantity =  TextData.continuityOfSmallResistanceHeaders.size();
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
