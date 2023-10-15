package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

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

    public void addTitlePageTable(PDPageContentStream content, PdfTitlePage titlePageData) throws IOException {

        yPosTitle = 660; // start position for 1st table component
        // add title
        addTableComponentTest(content, oneColTable, 25, yPosTitle,
                List.of(TextData.titlePageText.get(9)), Alignment.CENTER, commonColor,
                12, Font.fontBold); // add method for centering
        // add document number
        yPosTitle -= 40;
        textService.addSingleLineOfTextAlignment(content, titlePageData.getDocumentNumber(), yPosTitle,
                Alignment.CENTER, Font.fontBold, 14);
        // add customer name
        yPosTitle -= 60;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextData.titlePageText.get(0), titlePageData.getCustomerName())),
                3, commonColor, 10, Font.font); // replace with getter
        // add measurement place
        yPosTitle -= 50;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextData.titlePageText.get(1), titlePageData.getMeasurementPlace())),
                3, commonColor, 10, Font.font); // replace with getter
        // add measurement data
        yPosTitle -= 70;
        addTableComponentWithMultilineText(content, oneColTable, 60, yPosTitle,
                List.of(titlePageData.getTitlePageMeasurementTextData()),
                3, commonColor, 10, Font.font);
        // add decision
        yPosTitle -= 240;
        addTableComponentWithMultilineText(content, oneColTable, 200, yPosTitle,
                List.of(titlePageData.getDecisionTextData()), 3, commonColor, 10, Font.font);
    }

    public void addMeasurementTable(PDPageContentStream content, MeasurementMain measurementMain) throws IOException {

        yPosMeas = 700; // start position for 1st table component
        // add cascade name without measurement name
        addTableComponent(content, oneColTable, 25, yPosMeas,
                List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()),
                3, commonColor, 12, Font.fontBold);
        yPosMeas -= 30;
        // add measurement name
        addTableComponentTest(content, oneColTable, 25, yPosMeas, List.of(measurementMain.getMeasurementName()),
                Alignment.CENTER, commonColor, 11, Font.font);
        yPosMeas -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = pdfTableService.calculateCellSizes(measurementMain.getMeasurementName(), 10);
        addTableComponentTest(content, headerCellWidths, 25, yPosMeas,
                textService.getMeasurementEntryTableHeaders(measurementMain.getMeasurementName()),
                Alignment.CENTER, headerColor, 8, Font.fontBold);
        yPosMeas -= 30;
        // add main parameters
        addTableComponentTest(content, oneColTable, 25, yPosMeas,
                List.of(measurementMain.getPropertiesNamesAndValues()), Alignment.LEFT, commonColor, 9, Font.font);
        // add entry parameters
        yPosMeas -= 30;
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {
            addTableComponent(content, headerCellWidths, 25, yPosMeas,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(),
                    3, commonColor, 8, Font.font);
            yPosMeas -= 25;
        }
    }

    public void addElectricianPageTable(PDPageContentStream content, List<Electrician> electricians) throws IOException {
        // add header
        yPosElec = 720;
        addTableComponent(content, oneColTable, 22, yPosElec, List.of(TextData.electriciansPageText.get(2)),
                170, commonColor, 11, Font.font);
        // add table header
        yPosElec -= 30;
        int[] headerCellWidths = new int[]{50, 60, 100, 70, 70, 150}; // to do: automatic calculation
        addTableComponent(content, headerCellWidths, 25, yPosElec,
                TextData.electricianPdfTableHeaders, 3, headerColor, 10, Font.fontBold);
        // add electrician data
        yPosElec -= 70;
        for (Electrician electrician : electricians) {
            addTableComponentWithMultilineText(content, headerCellWidths, 60, yPosElec,
                    electrician.getElectricianDataTextList(), 3, commonColor, 8, Font.font);
            yPosElec -= 60;
        }
    }

    public void addHeaderTable(PDPageContentStream content, List<String> headingText, int yPos, int fontSize) throws IOException {

        addTableComponentWithMultilineText(content, oneColTable, 60, yPos,
                List.of(headingText), 3, commonColor, fontSize, Font.font);
    }

    public void addFooterTable(PDPageContentStream content) throws IOException {
        addTableComponent(content, oneColTable, 1, 50, List.of(""), 3, commonColor,
                10, Font.font);
    }

    private void addTableComponent(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
                                   int alignment, Color backgroundColor, int fontSize, PDFont fontType) throws IOException {

        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCell(textData.get(i).toString(), alignment, backgroundColor, fontSize, fontType);
        }
    }

    private void addTableComponentTest(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
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
