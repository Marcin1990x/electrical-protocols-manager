package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.EntryHeaderName;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.Table;

import java.awt.*;
import java.io.IOException;

@Service
public class PdfTableService {

    private EntryHeaderName headerName;

    public PdfTableService(EntryHeaderName headerName) {
        this.headerName = headerName;
    }

    public void addDataTable(PDPageContentStream content, MeasurementMain measurementMain, int fontSize) throws IOException {

        // add cascade name without measurement name
        Table cascadeTable = new Table(content);
        int[] cascadeCellWidths = {500}; // page width without margins
        cascadeTable.setTable(cascadeCellWidths, 25, 50, 660);
        cascadeTable.addCell(measurementMain.getMeasurementMainCascadeNameWithoutMeasurementName(), 3,
                new Color(255, 255, 255), 12);
        // add measurement name
        Table nameTable = new Table(content);
        int[] nameCellWidths = {500}; // page width without margins
        nameTable.setTable(nameCellWidths, 25, 50, 630);
        nameTable.addCell(measurementMain.getMeasurementName(), 200, new Color(255, 255, 255), 12);
        // add cascadeTable
            // add header
        Table headerTable = new Table(content);
        int[] headerCellWidths = calculateCellSizes(measurementMain.getMeasurementName());
        headerTable.setTable(headerCellWidths, 25, 50, 600);
        for(int i = 0; i < headerCellWidths.length; i++){
            headerTable
                    .addCell(headerName.getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders().get(i),3
                    , new Color(180, 179, 179), 10);
        }
            // add main parameters
        Table mainTable = new Table(content);
        int[] mainCellWidths = {500}; // page width without margins
        mainTable.setTable(mainCellWidths, 25, 50, 570);
        mainTable.addCell(measurementMain.getPropertiesNamesAndValues(), 3, new Color(255, 255, 255), 8);
            // add entry parameters

        int yPos = 540;
        for(int j = 0; j < measurementMain.getMeasurementEntries().size(); j++){

            Table entryTable = new Table(content);
            entryTable.setTable(headerCellWidths, 25, 50, yPos);

            for(int i = 0; i < headerCellWidths.length; i++){
                entryTable
                        .addCell(measurementMain.getMeasurementEntries().get(j).getEntryResultList().get(i).toString(),3
                                , new Color(255, 255, 255), 8);
            }
            yPos -= 25;
        }
    }

    private int[] calculateCellSizes(String measurementName) {

        int cellQuantity = 0;
        int maxTableSize = 500;

        if(measurementName.equals("Measurement Name 1")) {
            cellQuantity = headerName.getProtectionAgainstElectricShockByAutomaticShutdownEntryHeaders().size();
        }
        int[] cellWidths = new int[cellQuantity];

        for(int i = 0; i < cellQuantity; i++){
            cellWidths[i] = maxTableSize / cellQuantity;
        }
        return cellWidths;
    }
}
