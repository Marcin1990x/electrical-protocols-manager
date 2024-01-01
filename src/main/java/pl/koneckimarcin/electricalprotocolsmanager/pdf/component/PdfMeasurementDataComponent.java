package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.List;

import static pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle.headerColor;
import static pl.koneckimarcin.electricalprotocolsmanager.pdf.style.TablesStyle.oneColTable;

@Component
public class PdfMeasurementDataComponent {

    @Autowired
    private PdfTableService tableComponent;

    @Autowired
    private PdfTextService textService;

    public void appendMeasurementsDataToPages(PDDocument document, Building building, int measurementPagesQuantity, Font font) {

        PDPageContentStream content;

        List<MeasurementMain> measurementMainList = building.getMeasurementMainList();

        for (int page = 0; page < measurementPagesQuantity; page++) {

            try {
                content = new PDPageContentStream(document, document.getPage(page + 1),
                        PDPageContentStream.AppendMode.APPEND, false);

                TableProperties properties = new TablePropertiesBuilder()
                        .setContent(content)
                        .setCellHeight(25)
                        .setAlignment(Alignment.LEFT)
                        .setFontSize(12)
                        .build();

                addMeasurementTableToPage(properties, measurementMainList.get(page), font);

                content.close();
            } catch (IOException e) {
                System.out.println("Error when adding measurement data to page: " + page + e.getMessage());
            }
        }
    }

    private void addMeasurementTableToPage(TableProperties properties, MeasurementMain measurementMain, Font font) throws IOException {

        addCascadeName(properties, measurementMain, font);
        addMeasurementName(properties, measurementMain);
        addTableHeader(properties, measurementMain, font);
        boolean addedMainParameters = addMainParameters(properties, measurementMain, font);
        addEntryParameters(properties, measurementMain, addedMainParameters);
    }

    private void addCascadeName(TableProperties properties, MeasurementMain measurementMain, Font font) {

        int yPos = 700;
        properties.setYPosition(yPos);
        properties.setFont(font.getFont());
        properties.setTextData(List.of(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName()));

        tableComponent.addTableSinglelineText(properties);
    }

    private void addMeasurementName(TableProperties properties, MeasurementMain measurementMain) {

        int yPos = 670;
        properties.setYPosition(yPos);
        properties.setFontSize(11);
        properties.setAlignment(Alignment.CENTER);
        properties.setTextData(List.of(measurementMain.getMeasurementName()));

        tableComponent.addTableSinglelineText(properties);
    }

    private void addTableHeader(TableProperties properties, MeasurementMain measurementMain, Font font) {

        int yPos = 640;
        properties.setYPosition(yPos);
        // todo: refactor line below
        properties.setTextData(textService.getMeasurementEntryTableHeaders(measurementMain.getMeasurementName()));
        properties.setFont(font.getFontBold());
        properties.setFontSize(8);
        properties.setBackgroundColor(headerColor);
        properties.setCellWidths(measurementMain.getTableCellsSizes());

        tableComponent.addTableSinglelineText(properties);
    }

    private boolean addMainParameters(TableProperties properties, MeasurementMain measurementMain, Font font) {

        boolean mainParametersAdded = false;

        int yPos = 610;

        properties.setYPosition(yPos);
        properties.setFontSize(9);
        properties.setCellWidths(oneColTable);
        properties.setBackgroundColor(TablesStyle.commonColor);
        properties.setAlignment(Alignment.LEFT);
        properties.setFont(font.getFont());

        if (!hasMainProperties(measurementMain)) {

            properties.setTextData(List.of(measurementMain.getPropertiesNamesAndValues()));
            tableComponent.addTableSinglelineText(properties);
            mainParametersAdded = true;
        }
        return mainParametersAdded;
    }

    private boolean hasMainProperties(MeasurementMain measurementMain) {
        return measurementMain.getPropertiesNamesAndValues().isEmpty();
    }

    private void addEntryParameters(TableProperties properties, MeasurementMain measurementMain, boolean decreaseYposition) {

        int yPos;
        if (!decreaseYposition) {
            yPos = 610;
        } else {
            yPos = 580;
        }
        boolean moreThan20Entries = isSizeMoreThan20(measurementMain.getMeasurementEntries().size());

        int cellHeight;
        if (moreThan20Entries) {
            cellHeight = 15;
        } else {
            cellHeight = 25;
        }
        properties.setCellHeight(cellHeight);
        properties.setCellWidths(measurementMain.getTableCellsSizes());
        properties.setFontSize(8);
        properties.setDecreasedHeight(moreThan20Entries);

        for (int j = 0; j < measurementMain.getMeasurementEntries().size(); j++) {

            properties.setYPosition(yPos);
            properties.setTextData(measurementMain.getMeasurementEntries().get(j).getEntryResultList(j + 1));
            tableComponent.addTableSinglelineText(properties);

            yPos -= cellHeight;
        }
    }

    private boolean isSizeMoreThan20(int size) {
        return size > 20;
    }
}
