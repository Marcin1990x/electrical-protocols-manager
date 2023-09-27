package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;

import java.io.IOException;

@Service
public class PdfFooterService {

    private final PdfTextService textService;
    private final PdfTableComponent tableComponent;

    public PdfFooterService(PdfTextService textService, PdfTableComponent tableComponent) {
        this.textService = textService;
        this.tableComponent = tableComponent;
    }

    private final int footerFontSize = 9;

    public void addFooter(PDDocument document, PdfHeading heading) throws IOException {

        int pagesCount = document.getNumberOfPages();

        PDPageContentStream content;

        for (int i = 0; i < pagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addFooterTable(content);

            textService.addSingleLineOfText(content,
                    heading.getDocumentNumber() + "  " +  (i + 1) + "/" + pagesCount, 450, 40,
                            Font.font, footerFontSize);

            content.close();
        }
    }
}
