package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import pl.koneckimarcin.electricalprotocolsmanager.structure.Building;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfData {

    public static List<String> getData(Building building) {

        List<String> lines = new ArrayList<>();

        lines.add(building.getName());

        building.getFloors()
                .forEach(floor -> {
                    lines.add(floor.getName());
                    floor.getRooms()
                            .forEach(room -> {
                                lines.add(room.getName());
                                room.getMeasurementMains()
                                        .forEach(measurementMain -> {
                                            lines.add(measurementMain.toString());
                                            measurementMain.getMeasurementEntries()
                                                    .forEach(entry -> {
                                                        lines.add(entry.toString());
                                                    });
                                        });
                            });

                });
        return lines;
    }

    public static void printData(PDDocument document, PDPage page, List<String> text) throws IOException {

        PDFont pdFont = new PDType1Font(Standard14Fonts.FontName.COURIER);

        PDPageContentStream content = new PDPageContentStream(document, page);
        PdfDataService.addMultipleLineOfText(content, text, 50, 750, 10, pdFont, 10);
        content.close();
    }
}
