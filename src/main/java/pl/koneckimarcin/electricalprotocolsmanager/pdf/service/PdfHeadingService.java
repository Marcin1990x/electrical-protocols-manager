package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;

import java.io.IOException;

@Service
public class PdfHeadingService {

    private final int headingXposition = 50;
    private final int headingYposition = 750;
    private final int headingLeading = 10;
    private final int headingFontSize = 10;
    private final PDFont font = new PDType1Font(Standard14Fonts.FontName.COURIER);

    private PdfTextService textService;

    public PdfHeadingService(PdfTextService textService) {
        this.textService = textService;
    }

    public void addHeading(PDDocument document, PdfHeading heading) throws IOException {

        int pagesCount = document.getNumberOfPages();

        PDPageContentStream content;

        for (int i = 0; i < pagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            textService.addMultipleLineOfText(content, heading.getHeadingData(), headingXposition, headingYposition, headingLeading,
                    font, headingFontSize);

            content.close();
        }
    }
}
