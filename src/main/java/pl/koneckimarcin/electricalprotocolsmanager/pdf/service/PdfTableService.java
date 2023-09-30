package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.io.IOException;
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
        return cellWidths;
    }

    private int calculateCellQuantity(String measurementName) {

        if (measurementName.equals("Measurement Name 1")) { // case for each measurement main
            return TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders.size();
        } else if (measurementName.equals("ElectricianTable headers")) {
            return TextData.electricianPdfTableHeaders.size();
        } else {
            return 0;
        }
    }
}
