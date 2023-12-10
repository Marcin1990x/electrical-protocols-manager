package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;

import java.io.IOException;

@Service
public class PdfTitlePageService {

    private final PdfTableComponent tableComponent;
    private final PdfTextService textService;

    public PdfTitlePageService(PdfTableComponent tableComponent, PdfTextService textService) {
        this.tableComponent = tableComponent;
        this.textService = textService;
    }

    public void addTitlePage(PDDocument document, PdfTitlePage titlePageData, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(0),
                PDPageContentStream.AppendMode.APPEND, false);

        textService.addSingleLineOfTextAlignment(content, TextsPL.titlePageText.get(7),
                780, Alignment.CENTER, font.getFontBold(), 10);

        textService.addMultipleLineOfTextAlignment(content, titlePageData.getElectriciansTextData(), 765,
                Alignment.CENTER, 10, font.getFont(), 10); // add method for centering

        tableComponent.addTitlePageTable(content, titlePageData, font);

        content.close();
    }
}
