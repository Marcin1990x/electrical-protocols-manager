package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;

import java.io.IOException;
import java.util.List;

@Service
public class PdfMeasurementDataService {

    private final int headingXposition = 50;
    private final int headingYposition = 680;
    private final int headingLeading = 10;
    private final int headingFontSize = 10;
    private final PDFont font = new PDType1Font(Standard14Fonts.FontName.COURIER);

    private PdfTextService textService;

    public PdfMeasurementDataService(PdfTextService textService) {
        this.textService = textService;
    }

    public void addMeasurementData(PDDocument document, Building building, int measurementPagesCount) throws IOException {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for(int i = 0; i < measurementPagesCount; i++){

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);

            textService.addMultipleLineOfText(content, measurementMainList.get(i).getMeasurementsMainTextData(),
                    headingXposition, headingYposition, headingLeading,
                    font, headingFontSize);

            content.close();
        }
    }
}
