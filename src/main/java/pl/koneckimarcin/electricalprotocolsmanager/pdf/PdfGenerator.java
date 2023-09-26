package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfHeadingService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfMeasurementDataService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfService;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Room;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PdfGenerator {

    private final PdfService pdfService;
    private final PdfHeadingService headingService;
    private final PdfMeasurementDataService measurementDataService;

    public PdfGenerator(PdfService pdfService, PdfHeadingService headingService, PdfMeasurementDataService measurementDataService) {
        this.pdfService = pdfService;
        this.headingService = headingService;
        this.measurementDataService = measurementDataService;
    }

    public void createPdfDocument(String directory) throws IOException {

        ////////////////////////////// temporary place for data

        ProtectionMeasurementEntry measurement1 =
                new ProtectionMeasurementEntry(0, 0, 0, "Measure1",
                        Result.POSITIVE, 10, 10, 'D');
        ProtectionMeasurementEntry measurement2 =
                new ProtectionMeasurementEntry(2, 3, 4, "Measure2",
                        Result.NEGATIVE, 10, 10, 'C');

        ProtectionAgainstElectricShockByAutomaticShutdown protection =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 1,
                        10, 20, NetworkType.TNS, 2, 4);

        ProtectionAgainstElectricShockByAutomaticShutdown protection2 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 3,
                        12, 24, NetworkType.TNS, 2, 40);

        ProtectionAgainstElectricShockByAutomaticShutdown protection3 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 3,
                        12, 24, NetworkType.TNS, 2, 40);

        ProtectionAgainstElectricShockByAutomaticShutdown protection4 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 3,
                        12, 24, NetworkType.TNS, 2, 40);

        ProtectionAgainstElectricShockByAutomaticShutdown protection5 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(List.of(measurement1, measurement2), 3,
                        12, 24, NetworkType.TNS, 2, 40);

        PdfHeading headingData = new PdfHeading("RAP-0005-2023", new Date(System.currentTimeMillis()),
                List.of(new Electrician("Elektryk", "Pierwszy"), new Electrician("Elektryk", "Drugi")),
                "Domek nad jeziorem");

        Building buildingTest = new Building("Domek");

        Floor downFloor = new Floor("Down floor");
        Floor upperFloor = new Floor("Upper floor");

        buildingTest.addFloor(downFloor);
        buildingTest.addFloor(upperFloor);

        Room bath = new Room("Laznia");
        Room salon = new Room("Salon");
        Room bed = new Room("Sypialnia");
        Room cloth = new Room("Garderoba");

        downFloor.addRoom(bath);
        bath.addMeasurementMain(protection);
        downFloor.addRoom(salon);
        salon.addMeasurementMain(protection2);
        salon.addMeasurementMain(protection3);
        upperFloor.addRoom(bed);
        bed.addMeasurementMain(protection4);
        upperFloor.addRoom(cloth);
        cloth.addMeasurementMain(protection5);

        /////////////////////////////// temporary place for data

        PDDocument doc = new PDDocument();
        //count pages for rooms measurements
        int pagesCount = pdfService.calculateNumberOfPages(buildingTest);
        //add pages for title, theory...

        //create pages
        for (int i = 0; i < pagesCount; i++) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
        }

        File file = new File(directory);

        //add title page

        //add headers
        headingService.addHeading(doc, headingData);
        //add footers
        //add measurements
        measurementDataService.addMeasurementDataTableTest(doc, buildingTest, pagesCount);

        //add legend page/pages
        //add theory page/pages

        doc.save(file);
        doc.close();
    }
}
