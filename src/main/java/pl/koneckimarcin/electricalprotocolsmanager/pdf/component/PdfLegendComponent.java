package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextPropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfLegendComponent {

    private PdfTextService textService;

    public PdfLegendComponent(PdfTextService textService) {
        this.textService = textService;
    }

    private int yPos = 720;

    public void appendLegendPage(PDDocument document, int legendPage, List<MeasurementMain> measurementMainList, Font font) {

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
                .setLeading(10)
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

        List<String> addedMeasurements = new ArrayList<>();

        for (MeasurementMain measurement : measurementMainList) {
            if(isNotLegendForMeasurementAdded(addedMeasurements, measurement)) {
                addedMeasurements.add(addLegendForOneMeasurement(measurement, properties, font));
            }
        }
    }
    private boolean isNotLegendForMeasurementAdded(List<String> addedMeasurements, MeasurementMain measurementToAdd) {

        return addedMeasurements.stream().noneMatch(name -> name.equals(measurementToAdd.getMeasurementName()));
    }

    private String addLegendForOneMeasurement(MeasurementMain measurementMain, TextProperties properties, Font font) {

        properties.setFontType(font.getFontBold());
        addMeasurementNameForLegend(properties, measurementMain.getMeasurementName());

        yPos -= 18;

        properties.setyPosition(yPos);
        properties.setFontType(font.getFont());

        yPos += -25 + addLegendTextData(properties, measurementMain.getMeasurementLegend());
        properties.setyPosition(yPos);

        return measurementMain.getMeasurementName();
    }

    private void addMeasurementNameForLegend(TextProperties properties, String measurementName) {

        properties.setText(measurementName);
        properties.setFontSize(11);

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private int addLegendTextData(TextProperties properties, List<String> legendTextData) {

        properties.setFontSize(9);
        return textService.addMultipleLineOfTextAlignmentWithProperties(properties, legendTextData);
    }
}
