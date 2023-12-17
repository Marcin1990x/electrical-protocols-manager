package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementLegend;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
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

    public void addLegendData(PDDocument document, int legendPage, List<MeasurementMain> measurementMainList, Font font)
            throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(legendPage),
                PDPageContentStream.AppendMode.APPEND, false);

        List<String> measurementsListForLegend = measurementMainList
                .stream()
                .map(e -> e.getMeasurementName())
                .distinct()
                .toList();

        MeasurementLegend legend;

        textService.addSingleLineOfTextAlignment(content, TextsPL.legendPageText.get(0), yPos,
                Alignment.LEFT, font.getFontBold(), 12);

        yPos -= 20;

        for (String measure : measurementsListForLegend) {
            legend = new MeasurementLegend(measure);
            textService.addSingleLineOfTextAlignment(content, legend.getMeasurementName(), yPos,
                    Alignment.LEFT, font.getFontBold(), 11);
            yPos -= 15;
            yPos += -20 + textService.addMultipleLineOfTextAlignment(content, legend.getLegendText(), yPos,
                    Alignment.LEFT, 11, font.getFont(), 10);
            if (yPos <= 50)
                throw new IOException("Text outside available area.");
        }
        content.close();
        yPos = 720;
    }
}
