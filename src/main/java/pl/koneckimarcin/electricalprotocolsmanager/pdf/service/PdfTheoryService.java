package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfTheoryService {

    public void addTheory(PDDocument document, int startPage, int pages) throws IOException { // should depend on data?

        PDPageContentStream content;
        PDImageXObject image;

        for (int i = startPage; i < startPage + 2; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);
            image = PDImageXObject.createFromFile("src/main/resources/theoryImages/Sample.jpg", document);

            content.drawImage(image, 50, 100);

            content.close();
        }
    }
}
