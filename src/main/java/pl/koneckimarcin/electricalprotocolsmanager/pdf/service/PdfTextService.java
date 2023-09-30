package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfTextService {

    public void addSingleLineOfText(PDPageContentStream content, String text, int xPos, int yPos,
                                    PDFont pdFont, float fontSize) throws IOException {
        content.beginText();
        content.setFont(pdFont, fontSize);
        content.newLineAtOffset(xPos, yPos);
        content.showText(text);
        content.endText();
        content.moveTo(0, 0);
    }

    public void addMultipleLineOfText(PDPageContentStream content, List<String> text, int xPos, int yPos,
                                      float leading, PDFont pdFont, float fontSize) throws IOException {

        content.beginText();
        content.setFont(pdFont, fontSize);
        content.setLeading(leading);
        content.newLineAtOffset(xPos, yPos);
        for (String textLine : text) {
            content.showText(textLine);
            content.newLine();
        }
        content.endText();
        content.moveTo(0, 0);
    }

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int)(font.getStringWidth(text) / 1000 * fontSize);
    }

    public List<Integer> calculateHeadersWidth(List<Object> headers, PDFont font, int fontSize) throws IOException {

        List<Integer> headersWidth = new ArrayList<>();

        for(Object header : headers) {
            headersWidth.add(getTextWidth(header.toString(), font, fontSize));
        }
        return headersWidth;
    }

    public List<Object> getHeadersForCalculation(String nameForCalculation) {

        List<Object> headers = new ArrayList<>();

        if(nameForCalculation.equals("Measurement Name 1")) {
            headers = TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders;
        } else if (nameForCalculation.equals("ElectricianTable headers")) {
            headers = TextData.electricianPdfTableHeaders;
        }
        return headers;
    }
}
