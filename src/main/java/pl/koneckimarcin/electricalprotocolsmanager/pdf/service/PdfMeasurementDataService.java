package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfMeasurementsData;

import java.io.IOException;

public class PdfMeasurementDataService {

    public static void addMeasurementData(PDDocument document, PDPage page, PdfMeasurementsData measurementsData) throws IOException {

        PDFont pdFont = new PDType1Font(Standard14Fonts.FontName.COURIER);

        PDPageContentStream content = new PDPageContentStream(document, page);

        PdfTextService.addMultipleLineOfText(content, measurementsData.getMeasurementData(),
                50, 600, 10, pdFont, 10);

        content.close();
    }
}
