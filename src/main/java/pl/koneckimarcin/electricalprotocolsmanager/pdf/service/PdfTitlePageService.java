package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;

import java.io.IOException;

@Service
public class PdfTitlePageService {

    private final PdfTableComponent tableComponent;
    private final PdfTextService textService;

    public PdfTitlePageService(PdfTableComponent tableComponent, PdfTextService textService) {
        this.tableComponent = tableComponent;
        this.textService = textService;
    }

    public void addTitlePage(PDDocument document, PdfTitlePage titlePageData) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(0),
                PDPageContentStream.AppendMode.APPEND, false);

        textService.addSingleLineOfText(content, TextData.titlePageText.get(7), 220, 780, Font.font, 10);
        textService.addMultipleLineOfText(content, titlePageData.getElectriciansTextData(), 220, 765,
                10, Font.font, 10); // add method for centering

        tableComponent.addTitlePageTable(content, titlePageData);

        content.close();
    }
}