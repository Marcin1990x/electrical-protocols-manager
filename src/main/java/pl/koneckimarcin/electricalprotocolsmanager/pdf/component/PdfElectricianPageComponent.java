package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableComponent;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.io.IOException;
import java.util.List;

@Component
public class PdfElectricianPageComponent {

    private int yPosition = 720;
    private final int[] headerCellWidths = new int[]{50, 60, 100, 70, 70, 150}; //header cell widths

    @Autowired
    private PdfTableComponent tableComponent;

    public void appendElectriciansPage(PDDocument document, List<Electrician> electriciansList, int page, Font font) throws IOException {

        PDPageContentStream content;

        content = new PDPageContentStream(document, document.getPage(page),
                PDPageContentStream.AppendMode.APPEND, false);

        TablePropertiesBuilder builder = new TablePropertiesBuilder();
        TableProperties properties = builder
                .setContent(content)
                .setCellHeight(22)
                .setFontType(font)
                .build();

        addElectriciansTableToPage(content, electriciansList, font, properties);

        content.close();
    }

    private void addElectriciansTableToPage(PDPageContentStream content, List<Electrician> electricians, Font font,
                                            TableProperties properties)
            throws IOException {

        addTitleHeader(properties);

        addTableHeader(properties);

        addTableWithElectriciansData(content, font, electricians);
    }
    private void addTitleHeader(TableProperties properties) throws IOException {

        properties.setTextData(List.of(TextsPL.electriciansPageText.get(2)));
        tableComponent.addTableComponentWithProperties(properties);
    }
    private void addTableHeader(TableProperties properties) throws IOException {

        properties.setCellWidths(headerCellWidths);
        properties.setCellHeight(25);
        properties.setYPosition(690);
        properties.setTextData(TextsPL.electricianPdfTableHeaders);
        properties.setAlignment(Alignment.LEFT);
        properties.setBackgroundColor(TablesStyle.headerColor);
        properties.setFontSize(10);

        tableComponent.addTableComponentWithProperties(properties);

    }
    private void addTableWithElectriciansData(PDPageContentStream content, Font font, List<Electrician> electricians) throws IOException {

        yPosition = 620;

        for (Electrician electrician : electricians) {
            tableComponent.addTableComponentWithMultilineText(content, headerCellWidths, 60, yPosition,
                    electrician.getElectricianDataTextList(), 3, TablesStyle.commonColor, 8, font.getFont());
            yPosition -= 60;
        }
    }
}
