package pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

import java.awt.*;
import java.util.List;

public class TablePropertiesBuilder {

    private PDPageContentStream content = null;
    private int[] cellWidths = new int[]{500};
    private int cellHeight = 20;
    private int yPosition = 720;
    private List<Object> textData = List.of("");
    private int alignmentMultiline = 3;
    private Alignment alignment = Alignment.CENTER;
    private Color backgroundColor = new Color(255, 255, 255);
    private int fontSize = 11;
    private PDFont fontType = null;
    private boolean increasedHeight = false;

    public TablePropertiesBuilder setContent(PDPageContentStream content) {
        this.content = content;
        return this;
    }

    public TablePropertiesBuilder setCellWidths(int[] cellWidths) {
        this.cellWidths = cellWidths;
        return this;
    }

    public TablePropertiesBuilder setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
        return this;
    }

    public TablePropertiesBuilder setYPosition(int yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    public TablePropertiesBuilder setTextData(List<Object> textData) {
        this.textData = textData;
        return this;
    }

    public TablePropertiesBuilder setAlignmentMultiline(int alignmentMultiline) {
        this.alignmentMultiline = alignmentMultiline;
        return this;
    }

    public TablePropertiesBuilder setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TablePropertiesBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TablePropertiesBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public TablePropertiesBuilder setFontType(PDFont fontType) {
        this.fontType = fontType;
        return this;
    }

    public TablePropertiesBuilder setIncreasedHeight(boolean increasedHeight) {
        this.increasedHeight = increasedHeight;
        return this;
    }

    public TableProperties build() {
        TableProperties tableProperties = new TableProperties(content, cellWidths, cellHeight, yPosition, textData,
                alignmentMultiline, alignment, backgroundColor, fontSize, fontType, increasedHeight);
        content = null;
        cellWidths = new int[]{500};
        cellHeight = 20;
        yPosition = 700;
        textData = null;
        alignment = Alignment.CENTER;
        alignmentMultiline = 3;
        backgroundColor = new Color(255, 255, 255);
        fontSize = 11;
        fontType = null;
        increasedHeight = false;
        return tableProperties;
    }
}
