package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

    private final PdfTableService pdfTableService;
    private final PdfTextService textService;

    private int yPosMeas; // y position for measurements tables
    private int yPosTitle; // y position for title page tables

    public PdfTableComponent(PdfTableService pdfTableService, PdfTextService textService) {
        this.pdfTableService = pdfTableService;
        this.textService = textService;
    }

    private final Color commonColor = new Color(255, 255, 255); // color for tables backgrounds
    private final Color headerColor = new Color(180, 180, 180); // color for table header background

    public void addTitlePageTable(PDPageContentStream content, PdfTitlePage titlePageData) throws IOException {

        yPosTitle = 660; // start position for 1st table component
        // add title
        addTableComponent(content, new int[]{500}, 25, yPosTitle,
                List.of("Protokol z pomiarow ochronnych"),
                140, commonColor, 12, Font.font); // add method for centering
        // add document number
        yPosTitle -= 40;
        textService.addSingleLineOfText(content, titlePageData.getDocumentNumber(), 220, yPosTitle, Font.font, 14);
        // add customer name
        yPosTitle -= 60;
        addTableComponent(content, new int[]{500}, 40, yPosTitle, // add method for empty table
                List.of(""), 140, commonColor, 12, Font.font);
        textService.addMultipleLineOfText(content, List.of(TextData.titlePageText.get(0), titlePageData.getCustomerName()),
                53, yPosTitle + 20, 10, Font.font, 10);
        // add measurement place
        yPosTitle -= 50;
        addTableComponent(content, new int[]{500}, 40, yPosTitle,
                List.of(""), 140, commonColor, 12, Font.font);
        textService.addMultipleLineOfText(content, List.of(TextData.titlePageText.get(1), titlePageData.getMeasurementPlace()),
                53, yPosTitle + 25, 10, Font.font, 10);
        // add measurement data
        yPosTitle -= 70;
        addTableComponent(content, new int[]{500}, 60, yPosTitle,
                List.of(""), 140, commonColor, 12, Font.font);
        textService.addMultipleLineOfText(content, titlePageData.getTitlePageMeasurementTextData(),
                53, yPosTitle + 45, 12, Font.font, 10);
        // add decision
        yPosTitle -= 240;
        addTableComponent(content, new int[]{500}, 200, yPosTitle,
                List.of(""), 140, commonColor, 12, Font.font);
        textService.addMultipleLineOfText(content, List.of(TextData.titlePageText.get(8), titlePageData.getDecisionDescription()),
                53, yPosTitle + 185, 12, Font.font, 10);
    }

    public void addMeasurementTable(PDPageContentStream content, MeasurementMain measurementMain) throws IOException {

        yPosMeas = 700; // start position for 1st table component
        // add cascade name without measurement name
        addTableComponent(content, new int[]{500}, 25, yPosMeas,
                List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()),
                3, commonColor, 12, Font.font);
        yPosMeas -= 30;
        // add measurement name
        addTableComponent(content, new int[]{500}, 25, yPosMeas, List.of(measurementMain.getMeasurementName()),
                200, commonColor, 12, Font.font);
        yPosMeas -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = pdfTableService.calculateCellSizes(measurementMain.getMeasurementName());
        addTableComponent(content, headerCellWidths, 25, yPosMeas,
                TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders,
                3, headerColor, 10, Font.font);
        yPosMeas -= 30;
        // add main parameters
        addTableComponent(content, new int[]{500}, 25, yPosMeas,
                List.of(measurementMain.getPropertiesNamesAndValues()), 3, commonColor, 8, Font.font);
        // add entry parameters
        yPosMeas -= 30;
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {
            addTableComponent(content, headerCellWidths, 25, yPosMeas,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(),
                    3, commonColor, 8, Font.font);
            yPosMeas -= 25;
        }
    }

    public void addHeaderTable(PDPageContentStream content) throws IOException {
        addTableComponent(content, new int[]{500}, 60, 750, List.of(""), 3, commonColor,
                10, Font.font);
    }

    public void addFooterTable(PDPageContentStream content) throws IOException {
        addTableComponent(content, new int[]{500}, 1, 50, List.of(""), 3, commonColor,
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
}