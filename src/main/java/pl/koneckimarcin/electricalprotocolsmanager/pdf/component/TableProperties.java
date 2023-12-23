package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.awt.*;
import java.util.List;

public class TableProperties {

    private PDPageContentStream content;
    private int[] cellWidths;
    private int cellHeight;
    private int yPosition;
    private List<Object> textData;
    private Alignment alignment;
    private Color backgroundColor;
    private int fontSize;
    private Font font;
    private boolean increasedHeight;

    public TableProperties(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPosition,
                           List<Object> textData, Alignment alignment, Color backgroundColor,
                           int fontSize, Font font, boolean increasedHeight) {
        this.content = content;
        this.cellWidths = cellWidths;
        this.cellHeight = cellHeight;
        this.yPosition = yPosition;
        this.textData = textData;
        this.alignment = alignment;
        this.backgroundColor = backgroundColor;
        this.fontSize = fontSize;
        this.font = font;
        this.increasedHeight = increasedHeight;
    }

    public PDPageContentStream getContent() {
        return content;
    }

    public int[] getCellWidths() {
        return cellWidths;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public List<Object> getTextData() {
        return textData;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Font getFont() {
        return font;
    }

    public boolean isIncreasedHeight() {
        return increasedHeight;
    }

    public void setCellWidths(int[] cellWidths) {
        this.cellWidths = cellWidths;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setTextData(List<Object> textData) {
        this.textData = textData;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setIncreasedHeight(boolean increasedHeight) {
        this.increasedHeight = increasedHeight;
    }
}
