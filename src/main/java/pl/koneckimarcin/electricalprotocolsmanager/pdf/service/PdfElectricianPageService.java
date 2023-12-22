package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.io.IOException;
import java.util.List;

@Service
public class PdfElectricianPageService {

    private int yPosition;
    private final int[] headerCellWidths = new int[]{50, 60, 100, 70, 70, 150}; // electricians table header cell widths

    @Autowired
    private PdfTableComponent tableComponent;

    public void appendElectriciansPage(PDDocument document, List<Electrician> electriciansList, int page, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        addElectriciansTableToPage(content, electriciansList, font);

        content.close();
    }

    private void addElectriciansTableToPage(PDPageContentStream content, List<Electrician> electricians, Font font)
            throws IOException {
        // add header
        yPosition = 720;
        tableComponent.addTableComponent(content, TablesStyle.oneColTable, 22, yPosition,
                List.of(TextsPL.electriciansPageText.get(2)), Alignment.CENTER, TablesStyle.commonColor, 11,
                font.getFont(), false);
        // add table header
        yPosition = decreasePosition(yPosition, 30);
        tableComponent.addTableComponent(content, headerCellWidths, 25, yPosition,
                TextsPL.electricianPdfTableHeaders, Alignment.LEFT, TablesStyle.headerColor, 10, font.getFont(), false);
        // add electrician data
        yPosition = decreasePosition(yPosition, 70);
        for (Electrician electrician : electricians) {
            tableComponent.addTableComponentWithMultilineText(content, headerCellWidths, 60, yPosition,
                    electrician.getElectricianDataTextList(), 3, TablesStyle.commonColor, 8, font.getFont());
            yPosition = decreasePosition(yPosition, 60);
        }
    }
    private int decreasePosition(int actualPosition, int decreaseValue) {
       return actualPosition -= decreaseValue;
    }
}
