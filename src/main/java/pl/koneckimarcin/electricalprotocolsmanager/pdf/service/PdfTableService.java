package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.EntryHeaderName;

import java.io.IOException;
import java.util.List;

@Service
public class PdfTableService {

    private final PdfTextService textService;
    private final EntryHeaderName headerName;

    public PdfTableService(PdfTextService textService, EntryHeaderName headerName) {
        this.textService = textService;
        this.headerName = headerName;
    }

    private final PDFont fontType = new PDType1Font(Standard14Fonts.FontName.COURIER); // common font for text in tables
    private final int maxTableSize = 500; // page area width

    public int[] calculateCellSizes(String measurementName) throws IOException {

        int cellQuantity = calculateCellQuantity(measurementName);
        int[] cellWidths = new int[cellQuantity];

        List<Integer> headersWidth = textService.calculateHeadersWidth(headerName
                .getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders(), fontType, 10);

        int totalHeadersWidth = headersWidth.stream().mapToInt(Integer::intValue).sum();
        float resolution = (float) maxTableSize / totalHeadersWidth;

        for (int i = 0; i < cellQuantity; i++) {
            cellWidths[i] = (int) (headersWidth.get(i) * resolution);
        }
        return cellWidths;
    }

    private int calculateCellQuantity(String measurementName) {

        if (measurementName.equals("Measurement Name 1")) { // case for each measurement main
            return headerName.getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders().size();
        } else {
            return 0;
        }
    }
}
