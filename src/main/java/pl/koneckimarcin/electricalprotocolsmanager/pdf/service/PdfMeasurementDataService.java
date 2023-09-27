package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.io.IOException;
import java.util.List;

@Service
public class PdfMeasurementDataService {

    private final int measurementsXposition = 50;
    private final int measurementsYposition = 680;
    private final int measurementsLeading = 10;
    private final int measurementsFontSize = 10;

    private PdfTextService textService;
    private PdfTableComponent tableComponent;

    public PdfMeasurementDataService(PdfTextService textService, PdfTableComponent tableComponent) {
        this.textService = textService;
        this.tableComponent = tableComponent;
    }

    public void addMeasurementData(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for (int i = 0; i < measurementPagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            textService.addMultipleLineOfText(content, measurementMainList.get(i).getMeasurementsMainTextData(),
                    measurementsXposition, measurementsYposition, measurementsLeading,
                    Font.font, measurementsFontSize);

            content.close();
        }
    }

    public void addMeasurementDataTableTest(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for (int i = 0; i < measurementPagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addMeasurementTable(content, measurementMainList.get(i));

            content.close();
        }
    }
}
