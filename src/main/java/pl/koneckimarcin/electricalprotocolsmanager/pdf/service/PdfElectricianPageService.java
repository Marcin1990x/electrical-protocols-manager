package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.io.IOException;
import java.util.List;

@Service
public class PdfElectricianPageService {

    PdfTableComponent tableComponent;

    public PdfElectricianPageService(PdfTableComponent tableComponent) {
        this.tableComponent = tableComponent;
    }

    public void addData(PDDocument document, List<Electrician> electricians, int page, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        tableComponent.addElectricianPageTable(content, electricians, font);

        content.close();
    }
}
