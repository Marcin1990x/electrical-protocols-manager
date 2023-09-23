package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfHeadingService;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Room;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

        PDDocument pdfDocument = new PDDocument();
        PDPage page = new PDPage();
        PDPage page2 = new PDPage();
        pdfDocument.addPage(page);
        pdfDocument.addPage(page2);

        File file = new File(directory);

        //add title page
        //add heading
        PdfHeading heading = new PdfHeading("PE20230917", new Date(2023, 8, 17),
                List.of(new Electrician("Marcin", "Elektryk")), "Domek");
        PdfHeadingService.addHeading(heading, pdfDocument);
        //add footer
        //add measurements
/*        PdfMeasurementsData measurementsData = new PdfMeasurementsData();
        measurementsData.setMeasurementData(building);
        PdfMeasurementDataService.addMeasurementData(pdfDocument, page, measurementsData);*/
        //add legend
        //add theory

        pdfDocument.save(file);
        pdfDocument.close();
    }
}
