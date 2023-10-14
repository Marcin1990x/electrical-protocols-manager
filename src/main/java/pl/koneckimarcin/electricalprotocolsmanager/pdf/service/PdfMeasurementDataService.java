package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.io.IOException;
import java.util.List;

@Service
public class PdfMeasurementDataService {

    private PdfTextService textService;
    private PdfTableComponent tableComponent;

    public PdfMeasurementDataService(PdfTextService textService, PdfTableComponent tableComponent) {
        this.textService = textService;
        this.tableComponent = tableComponent;
    }

    public void addMeasurementDataTable(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for (int i = 0; i < measurementPagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i + 1),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addMeasurementTable(content, measurementMainList.get(i));

            content.close();
        }
    }
}
