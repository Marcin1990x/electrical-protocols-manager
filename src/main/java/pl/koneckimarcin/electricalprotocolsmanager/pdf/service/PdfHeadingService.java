package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfHeadingService {

    private final int headingYposition = 750;
    private final int headingFontSize = 10;

    private PdfTableComponent tableComponent;

    public PdfHeadingService(PdfTableComponent tableComponent) {
        this.tableComponent = tableComponent;
    }

    public void addHeading(PDDocument document, List<String> headingTextData) throws IOException {

        int pagesCount = document.getNumberOfPages();

        PDPageContentStream content;

        for (int i = 1; i < pagesCount; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            tableComponent.addHeaderTable(content, headingTextData, headingYposition, headingFontSize);

            content.close();
        }
    }
}
