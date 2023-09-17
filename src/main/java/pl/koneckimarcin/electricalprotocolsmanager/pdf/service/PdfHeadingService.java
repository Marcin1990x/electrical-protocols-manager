package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;

import java.io.IOException;

public class PdfHeadingService {
    public static void addHeading(PdfHeading heading, PDDocument pdfDocument) throws IOException {

        PDFont pdFont = new PDType1Font(Standard14Fonts.FontName.COURIER);

        int pagesCount = pdfDocument.getNumberOfPages();
        for (int i = 0; i < pagesCount; i++) {
            PDPageContentStream content = new PDPageContentStream(pdfDocument, pdfDocument.getPage(i));
            PdfTextService.addMultipleLineOfText(content, heading.getHeadingData(),
                    50, 750, 10, pdFont, 10);
            content.close();
        }
    }
}
