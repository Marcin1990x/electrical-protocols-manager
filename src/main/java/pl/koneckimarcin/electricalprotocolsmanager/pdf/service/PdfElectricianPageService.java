package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.io.IOException;
import java.util.List;

@Service
public class PdfElectricianPageService {

    PdfTableComponent tableComponent;

    public PdfElectricianPageService(PdfTableComponent tableComponent) {
        this.tableComponent = tableComponent;
    }

    public void addData(PDDocument document, List<Electrician> electricians, int page) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        tableComponent.addElectricianPageTable(content, electricians);

        content.close();
    }
}
