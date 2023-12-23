package pl.koneckimarcin.electricalprotocolsmanager.pdf.table;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.util.StringUtils;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.TableProperties;

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
    public void setTableWithProperties(TableProperties properties) {
        this.columnWidths = properties.getCellWidths();
        this.cellHeight = properties.getCellHeight();
        this.yPos = properties.getYPosition();
    }

    public void addCellAlignment(String text, Alignment alignment, Color fillColor, int fontSize, PDFont font,
        boolean increasedHeigth) throws IOException {

        Color fontColor = new Color(0, 0, 0);

        content.setNonStrokingColor(fillColor);

        int textWidth = getTextWidth(text, font, fontSize);
        boolean isSplitNeeded = isSplitNeeded(text, textWidth);

        int yPosText = yPos + 10;

        if(increasedHeigth) {
            yPosText -= 5;
        }

        if(isSplitNeeded)
            yPosText += 5;

        content.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        content.fillAndStroke();
        content.beginText();
        content.setNonStrokingColor(fontColor);
        content.setFont(font, fontSize);

        content.newLineAtOffset(xPos + calculateAlignmentPosition(alignment, columnWidths[columnPosition],
                text, font, fontSize, isSplitNeeded), yPosText);

        if (!isSplitNeeded) {
            content.showText(text);
            content.endText();
        } else {
            content.setLeading(10);
            printSplitedText(content, text);
        }
        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }
    public void addCellWithProperties(String text, TableProperties properties) throws IOException {

        Color fontColor = new Color(0, 0, 0);

        content.setNonStrokingColor(properties.getBackgroundColor());

        int textWidth = getTextWidth(text, properties.getFont().getFont(), properties.getFontSize());
        boolean isSplitNeeded = isSplitNeeded(text, textWidth);

        int yPosText = yPos + 10;

        if(properties.isIncreasedHeight()) {
            yPosText -= 5;
        }

        if(isSplitNeeded)
            yPosText += 5;

        content.addRect(xPos, yPos, columnWidths[columnPosition], cellHeight);

        content.fillAndStroke();
        content.beginText();
        content.setNonStrokingColor(fontColor);
        content.setFont(properties.getFont().getFont(), properties.getFontSize());

        content.newLineAtOffset(xPos + calculateAlignmentPosition(properties.getAlignment(), columnWidths[columnPosition],
                text, properties.getFont().getFont(), properties.getFontSize(), isSplitNeeded), yPosText);

        if (!isSplitNeeded) {
            content.showText(text);
            content.endText();
        } else {
            content.setLeading(10);
            printSplitedText(content, text);
        }
        xPos = xPos + columnWidths[columnPosition];
        columnPosition++;
    }

    private void printSplitedText(PDPageContentStream content, String text) throws IOException {

        String[] splitted;
        if (text.contains(" ")) {
            splitted = splitTextBySpace(text);
            for (String half : splitted) {
                content.showText(half);
                content.newLine();
            }
        } else {
            content.showText(text);
        }
        content.endText();
    }

    private boolean isSplitNeeded(String text, int textWidth) {
        return columnWidths[columnPosition] - textWidth < 10 && text.contains(" ");
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
                                           int fontSize, boolean needsSplit) throws IOException {

        int offset = 0;

        if (alignment == Alignment.CENTER) {
            if(needsSplit) {
                text = getSplitedText(text);
            }
            offset = columnWidth / 2 - getTextWidth(text, font, fontSize) / 2;
        } else if (alignment == Alignment.LEFT) {
            offset = 3;
        } else if (alignment == Alignment.RIGHT) {
            if(needsSplit) {
                text = getSplitedText(text);
            }
            offset = -3 + columnWidth - getTextWidth(text, font, fontSize);
        }
        return offset;
    }

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int) (font.getStringWidth(text) / 1000 * fontSize);
    }
    private String getSplitedText(String textToSplit) {

        String[] splitted = splitTextBySpace(textToSplit);
        if(splitted != null) {
            textToSplit =  Arrays.stream(splitted).max(Comparator.comparing(String::length)).orElseThrow();
        }
        return textToSplit;
    }

    private String[] splitTextBySpace(String text) {
        return StringUtils.split(text, " ");
    }
}
