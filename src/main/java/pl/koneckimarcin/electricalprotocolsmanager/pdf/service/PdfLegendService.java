package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementLegend;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.io.IOException;
import java.util.List;

@Service
public class PdfLegendService {

    private PdfTextService textService;

    public PdfLegendService(PdfTextService textService) {
        this.textService = textService;
    }

    private final int xPos = 53;
    private int yPos = 720;

    public void addLegendData(PDDocument document, int legendPage, List<MeasurementMain> measurementMainList) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(legendPage),
                PDPageContentStream.AppendMode.APPEND, false);

        List<String> measurementsListForLegend = measurementMainList
                .stream()
                .map(e -> e.getMeasurementName())
                .distinct()
                .toList();

        MeasurementLegend legend;

        textService.addSingleLineOfText(content, TextData.legendPageText.get(0), xPos, yPos, Font.font, 14);

        yPos -= 20;

        for (String measure : measurementsListForLegend) {
            legend = new MeasurementLegend(measure);
            textService.addSingleLineOfText(content, legend.getMeasurementName(), xPos, yPos, Font.font, 14);
            yPos -= 20;
            textService.addMultipleLineOfText(content, legend.getLegendText(), xPos, 680, 12, Font.font, 10);
            yPos -= 20;
        }
        content.close();
    }
}
