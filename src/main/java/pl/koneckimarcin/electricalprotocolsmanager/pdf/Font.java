package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

public class Font {

    private File fontFile;
    private File fontFileBold;

    private PDFont font;
    private PDFont fontBold;

    public Font(PDDocument document) throws IOException {
        fontFileBold = new File("arialbd.ttf");
        fontFile = new File("arial.ttf");
        setFont(document);
    }

    public void setFont(PDDocument document) throws IOException {
        font = PDType0Font.load(document, fontFile);
        fontBold = PDType0Font.load(document, fontFileBold);
    }

    public PDFont getFont() {
        return font;
    }

    public PDFont getFontBold() {
        return fontBold;
    }
}
