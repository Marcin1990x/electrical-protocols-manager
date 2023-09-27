package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;

import java.io.IOException;

@Service
public class PdfHeadingService {

    private final int headingXposition = 53;
    private final int headingYposition = 790;
    private final int headingLeading = 12;
    private final int headingFontSize = 10;

    private PdfTextService textService;
    private PdfTableComponent tableComponent;

    public PdfHeadingService(PdfTextService textService, PdfTableComponent tableComponent) {
        this.textService = textService;
        this.tableComponent = tableComponent;
    }

    public void addHeading(PDDocument document, PdfHeading heading) throws IOException {

        int pagesCount = document.getNumberOfPages();

        PDPageContentStream content;

        for (int i = 0; i < pagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addHeadingTable(content); // add empty table cell

            textService.addMultipleLineOfText(content, heading.getHeadingData(), headingXposition, headingYposition, headingLeading,
                    Font.font, headingFontSize); // add header text

            content.close();
        }
    }
}
