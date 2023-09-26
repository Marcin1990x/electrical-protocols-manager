package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.io.IOException;
import java.util.List;

@Service
public class PdfMeasurementDataService {

    private final int measurementsXposition = 50;
    private final int measurementsYposition = 680;
    private final int measurementsLeading = 10;
    private final int measurementsFontSize = 10;
    private final PDFont font = new PDType1Font(Standard14Fonts.FontName.COURIER);

    private PdfTextService textService;
    private PdfTableComponent tableService;

    public PdfMeasurementDataService(PdfTextService textService, PdfTableComponent tableService) {
        this.textService = textService;
        this.tableService = tableService;
    }

    public void addMeasurementData(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for(int i = 0; i < measurementPagesCount; i++){

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            textService.addMultipleLineOfText(content, measurementMainList.get(i).getMeasurementsMainTextData(),
                    measurementsXposition, measurementsYposition, measurementsLeading,
                    font, measurementsFontSize);

            content.close();
        }
    }

    public void addMeasurementDataTableTest(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for (int i = 0; i < measurementPagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableService.addTable(content, measurementMainList.get(i), measurementsFontSize);

            content.close();
        }
    }
}
