package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;

import java.io.IOException;
import java.util.List;

@Service
public class PdfTextService {

    private final int pageSize = 500;
    private final int xMargin = 50;

    public void addTextSingleline(TextProperties properties) {
        try {
            PDPageContentStream content = properties.getContentStream();

            content.beginText();
            content.setFont(properties.getFontType(), properties.getFontSize());
            content.newLineAtOffset(xMargin + calculateAlignmentPosition(properties.getAlignment(), properties.getText()
                    , properties.getFontType(), properties.getFontSize()), properties.getyPosition());
            content.showText(properties.getText());
            content.endText();
            content.moveTo(0, 0);
        } catch (IOException e) {
            System.out.println("Error when adding text: " + properties.getText() + "page. " + e.getMessage());
        }
    }

    public int addTextMultiline(TextProperties properties, List<String> text) {

        PDPageContentStream content = properties.getContentStream();
        int yPosOffset = 0;

        try {
            content.beginText();
            content.setFont(properties.getFontType(), properties.getFontSize());
            content.setLeading(properties.getLeading());
            content.newLineAtOffset(xMargin +
                            calculateAlignmentPosition(properties.getAlignment(), text.get(0), properties.getFontType(), properties.getFontSize()),
                    properties.getyPosition());
            for (String textLine : text) {
                content.showText(textLine);
                content.newLine();
                yPosOffset -= properties.getFontSize();
            }
            content.endText();
            content.moveTo(0, 0);

        } catch (IOException e) {
            System.out.println("Error when adding multiline text: " + text + " to page. " + e.getMessage());
        }
        return yPosOffset;
    }

    private int calculateAlignmentPosition(Alignment alignment, String text, PDFont font, int fontSize)
            throws IOException {

        int offset = 0;

        if (alignment == Alignment.CENTER) {
            offset = pageSize / 2 - getTextWidth(text, font, fontSize) / 2;
        } else if (alignment == Alignment.LEFT) {
            offset = 3;
        } else if (alignment == Alignment.RIGHT) {
            offset = pageSize - getTextWidth(text, font, fontSize);
        }
        return offset;
    }

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int) (font.getStringWidth(text) / 1000 * fontSize);
    }
}
