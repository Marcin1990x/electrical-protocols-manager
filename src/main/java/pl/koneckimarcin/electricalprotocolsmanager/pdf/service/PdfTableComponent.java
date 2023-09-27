package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

    private final TextData headerName;
    private final PdfTableService pdfTableService;
    private int yPos;

    public PdfTableComponent(TextData headerName, PdfTableService pdfTableService) {
        this.headerName = headerName;
        this.pdfTableService = pdfTableService;
    }

    // common font for text in tables - extract globally

    private final Color commonColor = new Color(255, 255, 255); // color for tables backgrounds
    private final Color headerColor = new Color(180, 180, 180); // color for table header background


    public void addMeasurementTable(PDPageContentStream content, MeasurementMain measurementMain) throws IOException {

        yPos = 700; // start position for 1st table component
        // add cascade name without measurement name
        addTableComponent(content, new int[]{500}, 25, yPos,
                List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()),
                3, commonColor, 12, Font.font);
        yPos -= 30;
        // add measurement name
        addTableComponent(content, new int[]{500}, 25, yPos, List.of(measurementMain.getMeasurementName()),
                200, commonColor, 12, Font.font);
        yPos -= 30;
        // add cascadeTable
        // add header
        int[] headerCellWidths = pdfTableService.calculateCellSizes(measurementMain.getMeasurementName());
        addTableComponent(content, headerCellWidths, 25, yPos,
                TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders,
                3, headerColor, 10, Font.font);
        yPos -= 30;
        // add main parameters
        addTableComponent(content, new int[]{500}, 25, yPos,
                List.of(measurementMain.getPropertiesNamesAndValues()), 3, commonColor, 8, Font.font);
        // add entry parameters
        yPos -= 30;
        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {
            addTableComponent(content, headerCellWidths, 25, yPos,
                    measurementMain.getMeasurementEntries().get(j).getEntryResultList(),
                    3, commonColor, 8, Font.font);
            yPos -= 25;
        }
    }

    public void addHeadingTable(PDPageContentStream content) throws IOException {
        addTableComponent(content, new int[]{500}, 60, 750, List.of(""), 3, commonColor,
                10, Font.font);
    }

    public void addTableComponent(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
                                  int alignment, Color backgroundColor, int fontSize, PDFont fontType) throws IOException {

        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCell(textData.get(i).toString(), alignment, backgroundColor, fontSize, fontType);
        }
    }
}
