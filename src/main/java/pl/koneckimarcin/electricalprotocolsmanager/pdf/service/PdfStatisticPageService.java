package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic.StatisticService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.List;

@Service
public class PdfStatisticPageService {

    private final PdfTextService textService;
    private final StatisticService statisticService;
    private int yPos = 720;

    public PdfStatisticPageService(PdfTextService textService, StatisticService statisticService) {
        this.textService = textService;
        this.statisticService = statisticService;
    }

    public void addStatisticDate(PDDocument document, Building building, int page, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        textService.addSingleLineOfTextAlignment(content, TextsPL.statisticPageText.get(0), yPos,
                Alignment.CENTER, font.getFont(), 13);
        yPos -= 40;
        List<List<String>> measurementsStatistics = statisticService.buildMeasurementsStatistics(building);
        for (List statistics : measurementsStatistics) {
            textService.addSingleLineOfTextAlignment(content, statistics.get(0).toString(), yPos,
                    Alignment.LEFT, font.getFont(), 12);
            yPos -= 20;
            yPos += -20 + textService.addMultipleLineOfTextAlignment(content, statistics.subList(1, statistics.size()),
                    yPos, Alignment.LEFT, 12, font.getFont(), 10);
        }
        content.close();
        yPos = 720;
    }
}
