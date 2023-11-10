package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;

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

    public void addFooter(PDDocument document, String documentNumber, Font font) throws IOException {

        int pagesCount = document.getNumberOfPages();

        PDPageContentStream content;

        for (int i = 0; i < pagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addFooterTable(content, font);

            textService.addSingleLineOfTextAlignment(content,
                    documentNumber + " " + (i + 1) + "/" + pagesCount, 40, Alignment.RIGHT,
                    font.getFontBold(), footerFontSize);
            content.close();
        }
    }
}
