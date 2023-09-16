package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfMeasurementsData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Room;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {


    public static void createPdfDocument(String directory) throws IOException {

        ////////////////////////////// temporary place for data

        ProtectionMeasurementEntry measurement1 =
                new ProtectionMeasurementEntry(0, 0, 0, "Test",
                        Result.POSITIVE, 10, 10, 'D');
        ProtectionMeasurementEntry measurement2 = measurement1;

        ProtectionAgainstElectricShockByAutomaticShutdown protection =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 1,
                        10, 20, NetworkType.TNS, 0, 0);

        Room kitchen = new Room(List.of(protection), "Kitchen");
        Room livingRoom = new Room(List.of(protection), "Living Room");
        Room bedroom = new Room(List.of(protection), "Bedroom");
        Room bathroom = new Room(List.of(protection), "Bathroom");

        Floor groundFloor = new Floor(List.of(kitchen, livingRoom), "Ground floor");
        Floor firstFloor = new Floor(List.of(bedroom, bathroom), "First floor");

        Building building = new Building(List.of(groundFloor, firstFloor), "Dom");

        ///////////////////////////////

        PDDocument pdf = new PDDocument();
        PDPage page = new PDPage();
        pdf.addPage(page);

        File file = new File(directory);

        //add title page
        //add heading
        //add footer
        //add measurements
        PdfMeasurementsData measurementsData = new PdfMeasurementsData();
        measurementsData.setMeasurementData(building);
        addMeasurementData(pdf, page, measurementsData);
        //add legend
        //add theory

        pdf.save(file);
        pdf.close();
    }

    private static void addMeasurementData(PDDocument document, PDPage page, PdfMeasurementsData measurementsData) throws IOException {

        PDFont pdFont = new PDType1Font(Standard14Fonts.FontName.COURIER);

        PDPageContentStream content = new PDPageContentStream(document, page);

        PdfTextService.addMultipleLineOfText(content, measurementsData.getMeasurementData(),
                50, 750, 10, pdFont, 10);

        content.close();
    }
}
