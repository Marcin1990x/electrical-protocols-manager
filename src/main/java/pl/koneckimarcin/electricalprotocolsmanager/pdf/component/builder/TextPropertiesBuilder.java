package pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;

public class TextPropertiesBuilder {

    private PDPageContentStream content = null;
    private String text = "";
    private int yPosition = 700;
    private Alignment alignment = Alignment.CENTER;
    private PDFont fontType = null;
    private int fontSize = 10;
    private int leading = 11;

    public TextPropertiesBuilder setContent(PDPageContentStream content) {
        this.content = content;
        return this;
    }

    public TextPropertiesBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public TextPropertiesBuilder setyPosition(int yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    public TextPropertiesBuilder setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextPropertiesBuilder setFontType(PDFont fontType) {
        this.fontType = fontType;
        return this;
    }

    public TextPropertiesBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public TextPropertiesBuilder setLeading(int leading) {
        this.leading = leading;
        return this;
    }

    public TextProperties build() {
        TextProperties textProperties = new TextProperties(content, text, yPosition, alignment, fontType, fontSize, leading);

        PDPageContentStream contentStream = null;
        String text = "";
        int yPosition = 700;
        Alignment alignment = Alignment.CENTER;
        PDFont fontType = null;
        int fontSize = 10;
        int leading = 11;

        return textProperties;
    }
}
