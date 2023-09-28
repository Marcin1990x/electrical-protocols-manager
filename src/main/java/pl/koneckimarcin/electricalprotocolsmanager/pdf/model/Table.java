package pl.koneckimarcin.electricalprotocolsmanager.pdf.model;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.IOException;

public class Table {

    private PDPageContentStream contentStream;
    private int[] columnWidths;
    private int cellHeight;
    private int yPos;
    private int xPos = 50; // start position in X
    private int columnPosition;

    public Table(PDPageContentStream contentStream) {
        this.contentStream = contentStream;
    }

    public void setTable(int[] columnWidths, int cellHeight, int yPos) {
        this.columnWidths = columnWidths;
        this.cellHeight = cellHeight;
        this.yPos = yPos;
    }

    public void addCell(String text, int alignment, Color fillColor, int fontSize, PDFont font) throws IOException {

        Color fontColor = new Color(0, 0, 0);

        contentStream.setNonStrokingColor(fillColor);

        contentStream.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        contentStream.fillAndStroke();
        contentStream.beginText();
        contentStream.setNonStrokingColor(fontColor);
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(xPos + alignment, yPos + 10);
        contentStream.showText(text);
        contentStream.endText();

        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }
}