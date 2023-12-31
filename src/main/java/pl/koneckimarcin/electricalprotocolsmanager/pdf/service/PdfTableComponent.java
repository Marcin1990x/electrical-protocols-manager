package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.table.Table;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfTableComponent {

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

    public void addTableComponent(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPos, List<Object> textData,
                                  Alignment alignment, Color backgroundColor, int fontSize, PDFont fontType,
                                  boolean decreasedHeight) throws IOException {

        Table table = new Table(content);
        table.setTable(cellWidths, cellHeight, yPos);
        for (int i = 0; i < cellWidths.length; i++) {
            table.addCellAlignment(textData.get(i).toString(), alignment, backgroundColor, fontSize, fontType, decreasedHeight);
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
                                                                 List<List<String>> textData) {
        Table table = new Table(properties);

        for (int i = 0; i < properties.getCellWidths().length; i++) {
            table.addCellWithMultilineTextWithProperties(properties, textData.get(i));
        }
    }
}
