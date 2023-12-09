package pl.koneckimarcin.electricalprotocolsmanager.pdf.table;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.util.StringUtils;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
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

    public void addCellAlignment(String text, Alignment alignment, Color fillColor, int fontSize, PDFont font)
            throws IOException {

        Color fontColor = new Color(0, 0, 0);

        content.setNonStrokingColor(fillColor);

        int textWidth = getTextWidth(text, font, fontSize);
        boolean splitNeeded = columnWidths[columnPosition] - textWidth < 10 && text.contains(" ");
        int yPosText = yPos + 10;

        if(splitNeeded)
            yPosText += 5;

        content.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        content.fillAndStroke();
        content.beginText();
        content.setNonStrokingColor(fontColor);
        content.setFont(font, fontSize);

        content.newLineAtOffset(xPos + calculateAlignmentPosition(alignment, columnWidths[columnPosition],
                text, font, fontSize, splitNeeded), yPosText);

        if (splitNeeded) {

            content.setLeading(10);

            String[] splitted;
            if (text.contains(" ")) {
                splitted = StringUtils.split(text, " ");
                for (String half : splitted) {
                    content.showText(half);
                    content.newLine();
                }
            } else {
                content.showText(text);
            }
            content.endText();
        } else {
            content.showText(text);
            content.endText();
        }
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
        content.setLeading(12); // extract
        content.newLineAtOffset(xPos + alignment, yPos + cellHeight - fontSize - 4);
        for (String textLine : text) {
            content.showText(textLine);
            content.newLine();
        }
        content.endText();

        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }

    private int calculateAlignmentPosition(Alignment alignment, int columnWidth, String text, PDFont font,
                                           int fontSize, boolean split) throws IOException {

        int offset = 0;

        if (alignment == Alignment.CENTER) {
            if(split) {
                text = getSplittedText(text);
            }
            offset = columnWidth / 2 - getTextWidth(text, font, fontSize) / 2;
        } else if (alignment == Alignment.LEFT) {
            offset = 3;
        } else if (alignment == Alignment.RIGHT) {
            if(split) {
                text = getSplittedText(text);
            }
            offset = -3 + columnWidth - getTextWidth(text, font, fontSize);
        }
        return offset;
    }

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int) (font.getStringWidth(text) / 1000 * fontSize);
    }
    private String getSplittedText(String textToSplit) {

        String[] splitted = StringUtils.split(textToSplit, " ");
        if(splitted != null) {
            textToSplit =  Arrays.stream(splitted).max(Comparator.comparing(String::length)).orElseThrow();
        }
        return textToSplit;
    }
}
