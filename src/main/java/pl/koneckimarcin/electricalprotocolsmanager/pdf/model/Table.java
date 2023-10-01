package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Table {

    private PDPageContentStream content;
    private int[] columnWidths;
    private int cellHeight;
    private int yPos;
    private int xPos = 50; // start position in X
    private int columnPosition;

    public Table(PDPageContentStream content) {
        this.content = content;
    }

    public void setTable(int[] columnWidths, int cellHeight, int yPos) {
        this.columnWidths = columnWidths;
        this.cellHeight = cellHeight;
        this.yPos = yPos;
    }
    public void addCell(String text, int alignment, Color fillColor, int fontSize, PDFont font) throws IOException {

        Color fontColor = new Color(0, 0, 0);

        content.setNonStrokingColor(fillColor);

        content.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        content.fillAndStroke();
        content.beginText();
        content.setNonStrokingColor(fontColor);
        content.setFont(font, fontSize);
        content.newLineAtOffset(xPos + alignment, yPos + 10);
        content.showText(text);
        content.endText();

        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }
    public void addCellWithMultilineText(List<String> text, int alignment, Color fillColor, int fontSize, PDFont font)
            throws IOException {

        Color fontColor = new Color(0, 0, 0);

        content.setNonStrokingColor(fillColor);

        content.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        content.fillAndStroke();
        content.beginText();
        content.setNonStrokingColor(fontColor);
        content.setFont(font, fontSize);
        content.setLeading(10); // extract
        content.newLineAtOffset(xPos + alignment, yPos + cellHeight - (fontSize * 2));
        for(String textLine : text) {
            content.showText(textLine);
            content.newLine();
        }
        content.endText();

        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }
}
