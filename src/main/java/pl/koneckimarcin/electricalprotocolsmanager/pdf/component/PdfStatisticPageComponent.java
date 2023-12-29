package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.statistic.StatisticService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextPropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.List;

@Component
public class PdfStatisticPageComponent {

    @Autowired
    private PdfTextService textService;
    @Autowired
    private StatisticService statisticService;

    private int yPos = 720;

    public void appendStatisticsPage(PDDocument document, Building building, int page, Font font) {

        PDPageContentStream content;

        try {
            content = new PDPageContentStream(document, document.getPage(page),
                    PDPageContentStream.AppendMode.APPEND, false);

            TextProperties properties = new TextPropertiesBuilder()
                    .setContent(content)
                    .setFontType(font.getFont())
                    .build();

            addTitlePage(properties, font);
            addStatistics(properties, building, font);

            content.close();
        } catch (IOException e) {
            System.out.println("Error when appending statistics page. " + e.getMessage());
        }

        yPos = 720;
    }

    private void addTitlePage(TextProperties properties, Font font) {

        int yPos = 720;

        properties.setyPosition(yPos);
        properties.setText(TextsPL.statisticPageText.get(0));
        properties.setFontSize(13);
        properties.setFontType(font.getFontBold());

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private void addStatistics(TextProperties properties, Building building, Font font) {

        int yPos = 680;

        List<List<String>> measurementsStatistics = statisticService.buildMeasurementsStatistics(building);
        for (List statistics : measurementsStatistics) {

            addStatisticsTitle(properties, statistics, yPos, font);
            yPos -= 20;
            yPos += -20 + addStatisticsData(properties, statistics, yPos, font);
        }
    }

    private void addStatisticsTitle(TextProperties properties, List statistics, int yPosition, Font font) {

        properties.setText(statistics.get(0).toString());
        properties.setyPosition(yPosition);
        properties.setAlignment(Alignment.LEFT);
        properties.setFontSize(12);
        properties.setFontType(font.getFontBold());

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private int addStatisticsData(TextProperties properties, List statistics, int yPostion, Font font) {

        properties.setyPosition(yPostion);
        properties.setLeading(12);
        properties.setFontSize(10);
        properties.setFontType(font.getFont());

        return textService.addMultipleLineOfTextAlignmentWithProperties(properties, statistics.subList(1, statistics.size()));

//        textService.addMultipleLineOfTextAlignment(content, statistics.subList(1, statistics.size()),
//                yPos, Alignment.LEFT, 12, font.getFont(), 10);
    }
}
