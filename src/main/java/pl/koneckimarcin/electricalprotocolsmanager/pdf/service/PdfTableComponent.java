package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.table.Table;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

    @Autowired
    private PdfTableService pdfTableService;
    @Autowired
    private PdfTextService textService;

    private int yPosMeas; // y position for measurements tables
    private int yPosTitle; // y position for title page tables
    private int yPosElec; // y position for electricians data page
    private final int[] oneColTable = new int[]{500};

    private final Color commonColor = new Color(255, 255, 255); // color for tables backgrounds
    private final Color headerColor = new Color(180, 180, 180); // color for table header background

    public void addTitlePageTable(PDPageContentStream content, PdfTitlePage titlePageData, Font font) throws IOException {

        yPosTitle = 660; // start position for 1st table component
        // add title
        addTableComponent(content, oneColTable, 25, yPosTitle,
                List.of(titlePageData.getTitle()), Alignment.CENTER, commonColor,
                12, font.getFontBold(), false);
        // add document number
        yPosTitle -= 40;
        textService.addSingleLineOfTextAlignment(content, titlePageData.getDocumentNumber(), yPosTitle,
                Alignment.CENTER, font.getFontBold(), 14);
        // add customer name
        yPosTitle -= 60;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextsPL.titlePageText.get(0), titlePageData.getCustomerName())),
                3, commonColor, 10, font.getFont()); // replace with getter
        // add measurement place
        yPosTitle -= 50;
        addTableComponentWithMultilineText(content, oneColTable, 40, yPosTitle,
                List.of(List.of(TextsPL.titlePageText.get(1), titlePageData.getMeasurementPlace())),
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
                Alignment.LEFT, commonColor, 12, font.getFont(), false);
        yPosMeas -= 30;
        // add measurement name
        addTableComponent(content, oneColTable, 25, yPosMeas, List.of(measurementMain.getMeasurementName()),
                Alignment.CENTER, commonColor, 11, font.getFont(), false);
        yPosMeas -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = pdfTableService.getCellSizes(measurementMain.getMeasurementName());
        addTableComponent(content, headerCellWidths, 25, yPosMeas,
                textService.getMeasurementEntryTableHeaders(measurementMain.getMeasurementName()),
                Alignment.CENTER, headerColor, 8, font.getFontBold(), false);
        yPosMeas -= 30;
        // add main parameters
        if (!measurementMain.getPropertiesNamesAndValues().isEmpty()) {
            addTableComponent(content, oneColTable, 25, yPosMeas,
                    List.of(measurementMain.getPropertiesNamesAndValues()), Alignment.LEFT,
                    commonColor, 9, font.getFont(), false);
            yPosMeas -= 30;
        }
        // add entry parameters
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {

            int cellHeight = 25;
            boolean moreThan20Entries = isSizeMoreThan20(measurementMain.getMeasurementEntries().size());

            if(moreThan20Entries) {
                cellHeight = 15;
            }

            addTableComponent(content, headerCellWidths, cellHeight, yPosMeas,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(j+1),
                    Alignment.LEFT, commonColor, 8, font.getFont(), moreThan20Entries);
            yPosMeas -= cellHeight;
        }
    }

    private boolean isSizeMoreThan20(int size) {
        return size > 20;
    }

    public void addHeaderTable(PDPageContentStream content, List<String> headingText, int yPos, int fontSize, Font font)
            throws IOException {

        addTableComponentWithMultilineText(content, oneColTable, 60, yPos,
                List.of(headingText), 3, commonColor, fontSize, font.getFont());
    }

    public void addTableComponent(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
                                   Alignment alignment, Color backgroundColor, int fontSize, PDFont fontType,
                                   boolean increasedHeight) throws IOException {

        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCellAlignment(textData.get(i).toString(), alignment, backgroundColor, fontSize, fontType, increasedHeight);
        }
    }
    public void addTableComponentWithProperties(TableProperties properties) {

        Table table = new Table(properties);

        for (int i = 0; i < properties.getCellWidths().length; i++) {
            table.addCellWithProperties(properties.getTextData().get(i).toString(), properties);
        }
    }

    public void addTableComponentWithMultilineText(PDPageContentStream content, int[] cellWidths, int cellHeight,
                                                    int yPos, List<List<String>> textData, int alignment, Color backgroundColor,
                                                    int fontSize, PDFont fontType) throws IOException {
        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCellWithMultilineText(textData.get(i), alignment, backgroundColor, fontSize, fontType);
        }
    }
    public void addTableComponentWithMultilineTextWithProperties(TableProperties properties,
                                                                 List<List<String>> electricianDataTextList) {
        Table table = new Table(properties);

        for (int i = 0; i < properties.getCellWidths().length; i++) {
            table.addCellWithMultilineTextWithProperties(properties, electricianDataTextList.get(i));
        }
    }
}
