package pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

public class TextProperties {

    private PDPageContentStream contentStream;
    private String text;
    private int yPosition;
    private Alignment alignment;
    private Font fontType;
    private float fontSize;
    private float leading;

    public TextProperties(PDPageContentStream contentStream, String text, int yPosition, Alignment alignment,
                          Font fontType, float fontSize, float leading) {
        this.contentStream = contentStream;
        this.text = text;
        this.yPosition = yPosition;
        this.alignment = alignment;
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.leading = leading;
    }

    public PDPageContentStream getContentStream() {
        return contentStream;
    }

    public void setContentStream(PDPageContentStream contentStream) {
        this.contentStream = contentStream;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Font getFontType() {
        return fontType;
    }

    public void setFontType(Font fontType) {
        this.fontType = fontType;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public float getLeading() {
        return leading;
    }

    public void setLeading(float leading) {
        this.leading = leading;
    }

    @Override
    public String toString() {
        return "TextProperties{" +
                "text='" + text + '\'' +
                ", yPosition=" + yPosition +
                ", alignment=" + alignment +
                ", fontType=" + fontType +
                ", fontSize=" + fontSize +
                ", leading=" + leading +
                '}';
    }
}
