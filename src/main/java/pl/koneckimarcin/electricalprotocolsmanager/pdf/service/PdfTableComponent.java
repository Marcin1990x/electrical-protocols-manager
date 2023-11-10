package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

    private final PdfTableService pdfTableService;
    private final PdfTextService textService;

    private int yPosMeas; // y position for measurements tables
    private int yPosTitle; // y position for title page tables
    private int yPosElec; // y position for electricians data page
    private final int[] oneColTable = new int[]{500};

    public PdfTableComponent(PdfTableService pdfTableService, PdfTextService textService) {
        this.pdfTableService = pdfTableService;
        this.textService = textService;
    }

    private final Color commonColor = new Color(255, 255, 255); // color for tables backgrounds
    private final Color headerColor = new Color(180, 180, 180); // color for table header background

    public void addTitlePageTable(PDPageContentStream content, PdfTitlePage titlePageData, Font font) throws IOException {

        yPosTitle = 660; // start position for 1st table component
        // add title
        addTableComponent(content, oneColTable, 25, yPosTitle,
                List.of(TextData.titlePageText.get(9)), Alignment.CENTER, commonColor,
                12, font.getFontBold()); // add method for centering
        // add document number
        yPosTitle -= 40;
        textService.addSingleLineOfTextAlignment(content, titlePageData.getDocumentNumber(), yPosTitle,
                Alignment.CENTER, font.getFontBold(), 14);
        // add customer name
        yPosTitle -= 60;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextData.titlePageText.get(0), titlePageData.getCustomerName())),
                3, commonColor, 10, font.getFont()); // replace with getter
        // add measurement place
        yPosTitle -= 50;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextData.titlePageText.get(1), titlePageData.getMeasurementPlace())),
                3, commonColor, 10, font.getFont()); // replace with getter
        // add measurement data
        yPosTitle -= 70;
        addTableComponentWithMultilineText(content, oneColTable, 60, yPosTitle,
                List.of(titlePageData.getTitlePageMeasurementTextData()),
                3, commonColor, 10, font.getFont());
        // add decision
        yPosTitle -= 240;
        addTableComponentWithMultilineText(content, oneColTable, 200, yPosTitle,
                List.of(titlePageData.getDecisionTextData()), 3, commonColor, 10, font.getFont());
    }

    public void addMeasurementTable(PDPageContentStream content, MeasurementMain measurementMain, Font font) throws IOException {

        yPosMeas = 700; // start position for 1st table component
        // add cascade name without measurement name
        addTableComponent(content, oneColTable, 25, yPosMeas,
                List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()),
                Alignment.LEFT, commonColor, 12, font.getFont());
        yPosMeas -= 30;
        // add measurement name
        addTableComponent(content, oneColTable, 25, yPosMeas, List.of(measurementMain.getMeasurementName()),
                Alignment.CENTER, commonColor, 11, font.getFont());
        yPosMeas -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = pdfTableService.getCellSizes(measurementMain.getMeasurementName());
        addTableComponent(content, headerCellWidths, 25, yPosMeas,
                textService.getMeasurementEntryTableHeaders(measurementMain.getMeasurementName()),
                Alignment.CENTER, headerColor, 8, font.getFontBold());
        yPosMeas -= 30;
        // add main parameters
        if (!measurementMain.getPropertiesNamesAndValues().isEmpty()) {
            addTableComponent(content, oneColTable, 25, yPosMeas,
                    List.of(measurementMain.getPropertiesNamesAndValues()), Alignment.LEFT,
                    commonColor, 9, font.getFont());
            yPosMeas -= 30;
        }
        // add entry parameters
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {
            addTableComponent(content, headerCellWidths, 25, yPosMeas,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(),
                    Alignment.LEFT, commonColor, 8, font.getFont());
            yPosMeas -= 25;
        }
    }

    public void addElectricianPageTable(PDPageContentStream content, List<Electrician> electricians, Font font)
            throws IOException {
        // add header
        yPosElec = 720;
        addTableComponent(content, oneColTable, 22, yPosElec,
                List.of(TextData.electriciansPageText.get(2)), Alignment.CENTER, commonColor, 11,
                font.getFont());
        // add table header
        yPosElec -= 30;
        int[] headerCellWidths = new int[]{50, 60, 100, 70, 70, 150}; // to do: automatic calculation
        addTableComponent(content, headerCellWidths, 25, yPosElec,
                TextData.electricianPdfTableHeaders, Alignment.LEFT, headerColor, 10, font.getFont());
        // add electrician data
        yPosElec -= 70;
        for (Electrician electrician : electricians) {
            addTableComponentWithMultilineText(content, headerCellWidths, 60, yPosElec,
                    electrician.getElectricianDataTextList(), 3, commonColor, 8, font.getFont());
            yPosElec -= 60;
        }
    }

    public void addHeaderTable(PDPageContentStream content, List<String> headingText, int yPos, int fontSize, Font font)
            throws IOException {

        addTableComponentWithMultilineText(content, oneColTable, 60, yPos,
                List.of(headingText), 3, commonColor, fontSize, font.getFont());
    }

    public void addFooterTable(PDPageContentStream content, Font font) throws IOException {
        addTableComponent(content, oneColTable, 1, 50, List.of(""), Alignment.RIGHT,
                commonColor, 10, font.getFont());
    }

    private void addTableComponent(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
                                   Alignment alignment, Color backgroundColor, int fontSize, PDFont fontType) throws IOException {

        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCellAlignment(textData.get(i).toString(), alignment, backgroundColor, fontSize, fontType);
        }
    }

    private void addTableComponentWithMultilineText(PDPageContentStream content, int[] cellWidths, int cellHeight,
                                                    int yPos, List<List<String>> textData, int alignment, Color backgroundColor,
                                                    int fontSize, PDFont fontType) throws IOException {
        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCellWithMultilineText(textData.get(i), alignment, backgroundColor, fontSize, fontType);
        }
    }
}
