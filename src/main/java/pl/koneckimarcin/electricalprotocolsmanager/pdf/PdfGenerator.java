package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

public class PdfGenerator {

    public static void createPdfDocument(String directory) throws IOException {

        PDDocument pdf = new PDDocument();

        pdf.addPage(new PDPage());
        File file = new File(directory);

        pdf.save(file);

        pdf.close();
    }
}
