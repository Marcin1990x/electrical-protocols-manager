package pl.koneckimarcin.electricalprotocolsmanager;

import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfGenerator;

import java.io.IOException;

public class Run {

    public static void run() throws IOException {

        //PdfGenerator.createPdfDocument("F:\\Programista\\Pomiary elektryczne\\new_pdf.pdf");
        PdfGenerator.createPdfDocument(
                "L:\\Projekty\\Programista\\electrical-protocols-manager\\new_pdf.pdf");
    }
}
