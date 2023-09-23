package pl.koneckimarcin.electricalprotocolsmanager.pdf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfGenerator;

import java.io.IOException;

@RestController
public class MainController {


    private PdfGenerator pdfGenerator;

    public MainController(PdfGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping("/")
    public void run() throws IOException {

        pdfGenerator.createPdfDocument("F:\\Programista\\Pomiary elektryczne\\test.pdf");
    }
}
