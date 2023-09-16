package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;
import java.util.List;

public class PdfDataService {

    public static void addSingleLineOfText(PDPageContentStream content, String text, int xPos, int yPos,
                                           PDFont pdFont, float fontSize) throws IOException {
        content.beginText();
        content.setFont(pdFont, fontSize);
        content.newLineAtOffset(xPos, yPos);
        content.showText(text);
        content.endText();
        content.moveTo(0, 0);
    }
    public static void addMultipleLineOfText(PDPageContentStream content, List<String> text, int xPos, int yPos,
                                             float leading, PDFont pdFont, float fontSize) throws IOException {

        content.beginText();
        content.setFont(pdFont, fontSize);
        content.setLeading(leading);
        content.newLineAtOffset(xPos, yPos);
        for(String textLine : text) {
            content.showText(textLine);
            content.newLine();
        }
        content.endText();
        content.moveTo(0, 0);
    }
}
