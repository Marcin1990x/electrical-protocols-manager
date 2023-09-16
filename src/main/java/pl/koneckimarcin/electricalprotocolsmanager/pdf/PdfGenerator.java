package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void createPdfDocument(String directory, List<String> textToPrint) throws IOException {

        PDDocument pdf = new PDDocument();

        PDPage page = new PDPage();

        pdf.addPage(page);
        File file = new File(directory);

        PdfData.printData(pdf, page, textToPrint);

        pdf.save(file);

        pdf.close();
    }
}
