package pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

public class TextPropertiesBuilder {

    private PDPageContentStream contentStream = null;
    private String text = "";
    private int yPosition = 700;
    private Alignment alignment = Alignment.CENTER;
    private Font fontType = null;
    private float fontSize = 10;
    private float leadind = 11;

    public TextPropertiesBuilder setContentStream(PDPageContentStream contentStream) {
        this.contentStream = contentStream;
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

    public TextPropertiesBuilder setFontType(Font fontType) {
        this.fontType = fontType;
        return this;
    }

    public TextPropertiesBuilder setFontSize(float fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public TextPropertiesBuilder setLeadind(float leadind) {
        this.leadind = leadind;
        return this;
    }

    public TextProperties build() {
        TextProperties textProperties = new TextProperties(contentStream, text, yPosition, alignment, fontType, fontSize, leadind);

        PDPageContentStream contentStream = null;
        String text = "";
        int yPosition = 700;
        Alignment alignment = Alignment.CENTER;
        Font fontType = null;
        float fontSize = 10;
        float leadind = 11;

        return textProperties;
    }
}
