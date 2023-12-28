package pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TableProperties {

    private PDPageContentStream content;
    private int[] cellWidths;
    private int cellHeight;
    private int yPosition;
    private List<Object> textData;
    private int alignmentMultiline;
    private Alignment alignment;
    private Color backgroundColor;
    private int fontSize;
    private PDFont font;
    private boolean decreasedHeight;

    public TableProperties(PDPageContentStream content, int[] cellWidths, int cellHeight, int yPosition,
                           List<Object> textData, int alignmentMultiline,
                           Alignment alignment, Color backgroundColor, int fontSize, PDFont font, boolean decreasedHeight) {
        this.content = content;
        this.cellWidths = cellWidths;
        this.cellHeight = cellHeight;
        this.yPosition = yPosition;
        this.textData = textData;
        this.alignmentMultiline = alignmentMultiline;
        this.alignment = alignment;
        this.backgroundColor = backgroundColor;
        this.fontSize = fontSize;
        this.font = font;
        this.decreasedHeight = decreasedHeight;
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

    public PDFont getFont() {
        return font;
    }

    public boolean isDecreasedHeight() {
        return decreasedHeight;
    }

    public void setCellWidths(int[] cellWidths) {
        this.cellWidths = cellWidths;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
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

    public void setFont(PDFont font) {
        this.font = font;
    }

    public void setDecreasedHeight(boolean increasedHeight) {
        this.decreasedHeight = increasedHeight;
    }

    public int getAlignmentMultiline() {
        return alignmentMultiline;
    }

    @Override
    public String toString() {
        return "TableProperties{" +
                "cellWidths=" + Arrays.toString(cellWidths) +
                ", cellHeight=" + cellHeight +
                ", yPosition=" + yPosition +
                ", textData=" + textData +
                ", alignmentMultiline=" + alignmentMultiline +
                ", alignment=" + alignment +
                ", backgroundColor=" + backgroundColor +
                ", fontSize=" + fontSize +
                ", font=" + font +
                ", increasedHeight=" + decreasedHeight +
                '}';
    }
}
