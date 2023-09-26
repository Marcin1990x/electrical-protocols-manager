package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.EntryHeaderName;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

    private final EntryHeaderName headerName;
    private final PdfTableService tableService;
    private int yPos = 660; // start position for 1st table component

    public PdfTableComponent(EntryHeaderName headerName, PdfTableService tableService) {
        this.headerName = headerName;
        this.tableService = tableService;
    }

    // common font for text in tables - extract globally
    private final PDFont fontType = new PDType1Font(Standard14Fonts.FontName.COURIER);

    private final Color commonColor = new Color(255, 255, 255); // color for tables backgrounds
    private final Color headerColor = new Color(180, 180, 180); // color for table header background


    public void addTable(PDPageContentStream content, MeasurementMain measurementMain, int fontSize) throws IOException {

        // add cascade name without measurement name
        addTableComponent(content, new int[]{500}, 25, yPos,
                List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()),
                3, commonColor, 12, fontType);
        yPos -= 30;
        // add measurement name
        addTableComponent(content, new int[]{500}, 25, yPos, List.of(measurementMain.getMeasurementName()),
                200, commonColor, 12, fontType);
        yPos -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = tableService.calculateCellSizes(measurementMain.getMeasurementName());
        addTableComponent(content, headerCellWidths, 25, yPos,
                headerName.getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders(),
                3, headerColor, 10, fontType);
        yPos -= 30;
        // add main parameters
        addTableComponent(content, new int[]{500}, 25, yPos,
                List.of(measurementMain.getPropertiesNamesAndValues()), 3, commonColor, 8, fontType);
        // add entry parameters
        yPos -= 30;
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {
            addTableComponent(content, headerCellWidths, 25, yPos,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(),
                    3, commonColor, 8, fontType);
            yPos -= 25;
        }
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
