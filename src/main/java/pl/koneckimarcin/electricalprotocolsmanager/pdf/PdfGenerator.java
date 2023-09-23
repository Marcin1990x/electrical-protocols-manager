package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.NetworkType;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.Result;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfHeading;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfHeadingService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfMeasurementDataService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfService;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;
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

    private PdfService pdfService;
    private PdfTextService pdfTextService;
    private PdfHeadingService headingService;
    private PdfMeasurementDataService measurementDataService;

    public PdfGenerator(PdfService pdfService, PdfTextService pdfTextService,
                        PdfHeadingService headingService, PdfMeasurementDataService measurementDataService) {
        this.pdfService = pdfService;
        this.pdfTextService = pdfTextService;
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

        Room kitchen = new Room(List.of(protection), "Kitchen");
        Room livingRoom = new Room(List.of(protection2), "Living Room");
        Room bedroom = new Room(List.of(protection), "Bedroom");
        Room bathroom = new Room(List.of(protection), "Bathroom");
        Room guestroom = new Room(List.of(protection2), "Guestroom");

        Floor groundFloor = new Floor(List.of(kitchen, livingRoom), "Ground floor");
        Floor firstFloor = new Floor(List.of(bedroom, bathroom, guestroom), "First floor");

        Building building = new Building(List.of(groundFloor, firstFloor), "Dom");

        PdfHeading headingData = new PdfHeading("RAP-0005-2023", new Date(System.currentTimeMillis()),
                List.of(new Electrician("Elektryk", "Pierwszy"), new Electrician("Elektryk", "Drugi")),
                "Domek nad jeziorem");

        /////////////////////////////// temporary place for data

        PDDocument doc = new PDDocument();
        //count pages for rooms measurements
        int pagesCount = pdfService.calculateNumberOfPages(building);
        //add pages for title, theory...

        //create pages
        for (int i = 0; i < pagesCount; i++) {
            PDPage page = new PDPage();
            doc.addPage(page);
        }

        File file = new File(directory);

        //add title page

        //add headers
        headingService.addHeading(doc, headingData);
        //add footers
        //add measurements
        measurementDataService.addMeasurementData(doc, building, pagesCount);
        //add legend page/pages
        //add theory page/pages

        doc.save(file);
        doc.close();
    }
}
