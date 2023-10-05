package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.CircuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.model.Room;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Electrician;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.model.Position;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PdfGenerator {

    private final PdfService pdfService;
    private final PdfHeadingService headingService;
    private final PdfFooterService footerService;
    private final PdfMeasurementDataService measurementDataService;
    private final PdfTitlePageService titlePageService;
    private final PdfLegendService legendService;
    private final PdfTheoryService theoryService;
    private final PdfElectricianPageService electricianPageService;
    private final PdfStatisticPageService statisticPageService;

    public PdfGenerator(PdfService pdfService, PdfHeadingService headingService, PdfFooterService footerService,
                        PdfMeasurementDataService measurementDataService, PdfTitlePageService titlePageService,
                        PdfLegendService legendService, PdfTheoryService theoryService,
                        PdfElectricianPageService electricianPageService, PdfStatisticPageService statisticPageService) {
        this.pdfService = pdfService;
        this.headingService = headingService;
        this.footerService = footerService;
        this.measurementDataService = measurementDataService;
        this.titlePageService = titlePageService;
        this.legendService = legendService;
        this.theoryService = theoryService;
        this.electricianPageService = electricianPageService;
        this.statisticPageService = statisticPageService;
    }

    public void createPdfDocument(String directory) throws IOException {

        ////////////////////////////// temporary place for data

        CircuitInsulationResistanceTnsEntry measurement3 = new CircuitInsulationResistanceTnsEntry(
                1, "", Result.POSITIVE, "", 978, 1044, 1048, 909,
                975, 1079, 1002, 994, 1040, 988, 10.0f);

        CircuitInsulationResistanceTns insulation1 = new CircuitInsulationResistanceTns(500);
        insulation1.setMeasurementEntries(List.of(measurement3));

        ProtectionMeasurementEntry measurement1 =
                new ProtectionMeasurementEntry(1, "", Result.POSITIVE, "Pokoj 1 GN 1",
                        "S301", 'B', 16.0f,
                        80.0f, 0.37f, 2.88f, 621.61f);

        ProtectionMeasurementEntry measurement2 =
                new ProtectionMeasurementEntry(2, "", Result.NEGATIVE, "Pokoj 1 GN 2",
                        "S301", 'B', 16.0f,
                        80.0f, 0.32f, 2.88f, 547.50f);

        ProtectionAgainstElectricShockByAutomaticShutdown protection =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f,  NetworkType.TNS);
        protection.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection2 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f,  NetworkType.TNS);
        protection2.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection3 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f,  NetworkType.TNS);
        protection3.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection4 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f,  NetworkType.TNS);
        protection4.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection5 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f,  NetworkType.TNS);
        protection5.setMeasurementEntries(List.of(measurement1, measurement2));

        Electrician electrician = new Electrician(
                "Elektryk", "Pierwszy",
                "Przykladowy adres 1",
                List.of("E/244/10/20", "E/244/08/10"),
                Position.CHECKER);
        Electrician electrician2 = new Electrician(
                "Elektryk", "Drugi",
                "Przykladowy adres 100",
                List.of("E/244/10/22", "E/244/90/10"),
                Position.MEASURER);


        PdfTitlePage titlePageData = new PdfTitlePage(
                List.of(electrician, electrician2),
                "RAP-0005-2023",
                "Protokol z pomiarow ochronnych",
                "Klient kliencki",
                "Domek nad jeziorem",
                TypeOfMeasurement.PERIODIC,
                TypeOfWeather.CLOUDY,
                LocalDate.now(),
                TypeOfInstallation.EXISTING,
                "1. Instalacja nadaje sie do uzytku",
                "1. W dwoch gniazdach brak napiecia"
        );
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
        bed.addMeasurementMain(insulation1);
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
        for (int i = 0; i < pagesCount + 6; i++) { // +1 for title page, +1 for legend page, +2 for theory, +1 for electricians, +1 for statistics
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
        }

        File file = new File(directory);

        //add title page
        titlePageService.addTitlePage(doc, titlePageData);
        //add headers
        headingService.addHeading(doc, titlePageData.getHeadingTextData());
        //add footers
        footerService.addFooter(doc, titlePageData.getDocumentNumber());
        //add measurements
        measurementDataService.addMeasurementDataTableTest(doc, buildingTest, pagesCount);
        //add legend page/pages
        legendService.addLegendData(doc, 6, buildingTest.getMeasurementMainList()); // hardcoded page
        //add theory page/pages
        theoryService.addTheory(doc, 7, 2); // hardcoded page
        //add electricians page
        electricianPageService.addData(doc, List.of(electrician, electrician2), 9); // hardcoded page
        //add statistic page LAST !!!
        statisticPageService.addStatisticDate(doc, buildingTest, 10); // hardcoded page

        doc.save(file);
        doc.close();
    }
}
