package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableComponent;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

import java.io.IOException;
import java.util.List;

@Component
public class PdfElectricianPageComponent {

    @Autowired
    private PdfTableComponent tableComponent;

    public void appendElectriciansPage(PDDocument document, List<Electrician> electriciansList, int page, Font font) {

        PDPageContentStream content;

        try {
            content = new PDPageContentStream(document, document.getPage(page),
                    PDPageContentStream.AppendMode.APPEND, false);

            TablePropertiesBuilder builder = new TablePropertiesBuilder();
            TableProperties properties = builder
                    .setContent(content)
                    .setCellHeight(22)
                    .setFontType(font.getFont())
                    .build();

            addElectriciansTableToPage(electriciansList, properties);

            content.close();
        } catch (IOException e) {
            System.out.println("Error when appending electricians page. " + e.getMessage());
        }
    }

    private void addElectriciansTableToPage(List<Electrician> electricians, TableProperties properties) {

        addTitleHeader(properties);

        addTableHeader(properties);

        addTableWithElectriciansData(electricians, properties);
    }
    private void addTitleHeader(TableProperties properties) {

        properties.setTextData(List.of(TextsPL.electriciansPageText.get(2)));
        tableComponent.addTableComponentWithProperties(properties);
    }
    private void addTableHeader(TableProperties properties) {

        int[] headerCellWidths = new int[]{50, 60, 100, 70, 70, 150}; //header cell widths

        properties.setCellWidths(headerCellWidths);
        properties.setCellHeight(25);
        properties.setYPosition(690);
        properties.setTextData(TextsPL.electricianPdfTableHeaders);
        properties.setAlignment(Alignment.LEFT);
        properties.setBackgroundColor(TablesStyle.headerColor);
        properties.setFontSize(10);

        tableComponent.addTableComponentWithProperties(properties);

    }
    private void addTableWithElectriciansData(List<Electrician> electricians, TableProperties properties) {

        properties.setCellHeight(60);
        properties.setYPosition(620);
        properties.setBackgroundColor(TablesStyle.commonColor);
        properties.setFontSize(8);

        for(Electrician electrician : electricians) {

            tableComponent.addTableComponentWithMultilineTextWithProperties(properties, electrician.getElectricianDataTextList());
            properties.setYPosition(decreaseNumber(properties.getYPosition(), 60));
        }
    }
    private int decreaseNumber(int actual, int decrease) {
        return actual =- decrease;
    }
}
