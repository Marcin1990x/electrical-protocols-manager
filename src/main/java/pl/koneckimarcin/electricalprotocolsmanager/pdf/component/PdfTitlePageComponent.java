package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextPropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.io.IOException;
import java.util.List;

@Component
public class PdfTitlePageComponent {

    private final PdfTableService tableComponent;
    private final PdfTextService textService;

    public PdfTitlePageComponent(PdfTableService tableComponent, PdfTextService textService) {
        this.tableComponent = tableComponent;
        this.textService = textService;
    }

    public void appendTitlePage(PDDocument document, PdfTitlePage titlePageData, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(0),
                PDPageContentStream.AppendMode.APPEND, false);

        TextProperties textProperties = new TextPropertiesBuilder()
                .setContent(content)
                .setFontType(font.getFontBold())
                .build();

        addElectriciansDataToTitlePage(font, titlePageData, textProperties);

        TableProperties tableProperties = new TablePropertiesBuilder()
                .setContent(content)
                .setCellHeight(25)
                .setFontType(font.getFontBold())
                .setFontSize(12)
                .build();


        addTitlePageTables(titlePageData, font, textProperties, tableProperties);

        content.close();
    }

    private void addElectriciansDataToTitlePage(Font font, PdfTitlePage titlePageData, TextProperties textProperties) {

        int yPosition = 780;

        textProperties.setText(TextsPL.titlePageText.get(7));
        textProperties.setyPosition(yPosition);

        textService.addTextSingleline(textProperties);

        yPosition -= 24;

        textProperties.setFontType(font.getFont());
        textProperties.setFontSize(10);
        textProperties.setyPosition(yPosition);

        textService.addTextMultiline(textProperties, titlePageData.getElectriciansTextData());
    }

    private void addTitlePageTables(PdfTitlePage titlePageData, Font font, TextProperties textProperties,
                                    TableProperties tableProperties) {

        int yPosition = 660;
        addTitleTable(yPosition, tableProperties, titlePageData);

        yPosition -= 40;
        addDocumentNumber(yPosition, textProperties, titlePageData, font);

        yPosition -= 60;
        addClientTable(yPosition, tableProperties, titlePageData, font);

        yPosition -= 50;
        addMeasurementPlace(yPosition, tableProperties, titlePageData);

        yPosition -= 70;
        addMeasurementData(yPosition, tableProperties, titlePageData);

        yPosition -= 240;
        addDecisionData(yPosition, tableProperties, titlePageData);
    }

    private void addTitleTable(int yPosition, TableProperties tableProperties, PdfTitlePage titlePageData) {

        tableProperties.setTextData(List.of(titlePageData.getTitle()));
        tableProperties.setYPosition(yPosition);
        tableComponent.addTableSinglelineText(tableProperties);
    }

    private void addDocumentNumber(int yPosition, TextProperties textProperties, PdfTitlePage titlePageData, Font font) {

        textProperties.setyPosition(yPosition);
        textProperties.setText(titlePageData.getDocumentNumber());
        textProperties.setFontSize(14);
        textProperties.setFontType(font.getFontBold());
        textService.addTextSingleline(textProperties);
    }

    private void addClientTable(int yPosition, TableProperties tableProperties, PdfTitlePage titlePageData, Font font) {

        tableProperties.setFontSize(10);
        tableProperties.setFont(font.getFont());
        tableProperties.setYPosition(yPosition);
        tableProperties.setCellHeight(40);
        tableComponent.addTableMultilineText(tableProperties,
                List.of(List.of(TextsPL.titlePageText.get(0), titlePageData.getCustomerName())));
    }

    private void addMeasurementPlace(int yPosition, TableProperties tableProperties, PdfTitlePage titlePageData) {

        tableProperties.setYPosition(yPosition);
        tableComponent.addTableMultilineText(tableProperties,
                List.of(List.of(TextsPL.titlePageText.get(1), titlePageData.getMeasurementPlace())));
    }

    private void addMeasurementData(int yPosition, TableProperties tableProperties, PdfTitlePage titlePageData) {

        tableProperties.setYPosition(yPosition);
        tableProperties.setCellHeight(60);
        tableComponent.addTableMultilineText(tableProperties,
                List.of(titlePageData.getTitlePageMeasurementTextData()));
    }

    private void addDecisionData(int yPosition, TableProperties tableProperties, PdfTitlePage titlePageData) {

        tableProperties.setYPosition(yPosition);
        tableProperties.setCellHeight(200);
        tableComponent.addTableMultilineText(tableProperties,
                List.of(titlePageData.getDecisionTextData()));
    }
}
