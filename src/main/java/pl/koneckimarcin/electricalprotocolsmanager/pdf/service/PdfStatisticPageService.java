package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.io.IOException;

@Service
public class PdfStatisticPageService {

    private PdfTextService textService;

    public PdfStatisticPageService(PdfTextService textService) {
        this.textService = textService;
    }

    public void addStatisticDate(PDDocument document, Building building, int page) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        textService.addSingleLineOfTextAlignment(content, TextData.statisticPageText.get(0), 720,
                Alignment.CENTER, Font.font, 14);

        textService.addSingleLineOfTextAlignment(content, "Punkty: " +
                        building.getMeasurementMainList().get(0).getMeasurementStatistics()[0], 700,
                Alignment.LEFT, Font.font, 14);
        textService.addSingleLineOfTextAlignment(content, "Pozytywy: " +
                        building.getMeasurementMainList().get(0).getMeasurementStatistics()[1], 680,
                Alignment.LEFT, Font.font, 14);


        content.close();
    }
}
