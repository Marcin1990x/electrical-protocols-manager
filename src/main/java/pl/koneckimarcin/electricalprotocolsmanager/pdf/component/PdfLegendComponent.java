package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementLegend;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextPropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

@Component
public class PdfLegendComponent {

    private PdfTextService textService;

    public PdfLegendComponent(PdfTextService textService) {
        this.textService = textService;
    }

    private int yPos = 720;

    public void addLegendData(PDDocument document, int legendPage, List<MeasurementMain> measurementMainList, Font font) {

        PDPageContentStream content;

        try {
            content = new PDPageContentStream(document, document.getPage(legendPage),
                    PDPageContentStream.AppendMode.APPEND, false);

            addLegendToPage(content, measurementMainList, font);

            content.close();

        } catch (IOException e) {
            System.out.println("Error when appending legend to page. " + e.getMessage());
        }
    }

    private void addLegendToPage(PDPageContentStream content, List<MeasurementMain> measurementMainList, Font font) throws IOException {

        TextProperties properties = new TextPropertiesBuilder()
                .setContent(content)
                .setText(TextsPL.legendPageText.get(0))
                .setyPosition(yPos)
                .setAlignment(Alignment.LEFT)
                .setFontType(font.getFontBold())
                .setFontSize(12)
                .build();

        addLegendPageTitle(properties);

        yPos -= 20;
        properties.setyPosition(yPos);

        addLegendForAllMeasurements(properties, measurementMainList, font);

        yPos = 720;
    }

    private void addLegendPageTitle(TextProperties properties) {

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private void addLegendForAllMeasurements(TextProperties properties, List<MeasurementMain> measurementMainList, Font font) {

        List<String> distinctMeasurements = extractDistinctMeasurementNames(measurementMainList);

        try {
            for (String measure : distinctMeasurements) {
                addLegendForOneMeasurement(measure, properties, font);
            }
        } catch (InvalidObjectException e) {
            System.out.println("Error when appending legend to page. Legend out of page size." + e.getMessage());
        }
    }

    private void addLegendForOneMeasurement(String measurementName, TextProperties properties, Font font) throws InvalidObjectException {

        MeasurementLegend legend;
        legend = new MeasurementLegend(measurementName);

        properties.setFontType(font.getFontBold());
        addMeasurementNameForLegend(properties, legend.getMeasurementName());

        yPos -= 20;

        properties.setyPosition(yPos);
        properties.setFontType(font.getFont());

        yPos += -5 + addLegendTextData(properties, legend.getLegendText());
        properties.setyPosition(yPos);

        if (yPos <= 50);
            throw new InvalidObjectException("Text outside available area.");
    }

    private void addMeasurementNameForLegend(TextProperties properties, String measurementName) {

        properties.setText(measurementName);
        properties.setFontSize(11);

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private int addLegendTextData(TextProperties properties, List<String> legendTextData) {

        return textService.addMultipleLineOfTextAlignmentWithProperties(properties, legendTextData);
    }

    private List<String> extractDistinctMeasurementNames(List<MeasurementMain> measurementMainList) {

        return measurementMainList
                .stream()
                .map(MeasurementMain::getMeasurementName)
                .distinct()
                .toList();
    }
}
