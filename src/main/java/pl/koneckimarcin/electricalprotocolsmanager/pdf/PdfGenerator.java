package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.*;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.main.CircuitInsulationResistanceTnc;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTnc.entry.CircuitInsulationResistanceTncEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.CircuitInsulationResistanceTns;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.circuitInsulationResistanceTns.CircuitInsulationResistanceTnsEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.ContinuityOfSmallResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.continuityOfSmallResistancy.ContinuityOfSmallResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.entry.MeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionAgainstElectricShockByAutomaticShutdown;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protectionAgainstElectricShockByAutomaticShutdown.ProtectionMeasurementEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.ResidualCurrentProtectionParameters;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.residualCurrentProtectionParameters.ResidualCurrentProtectionParametersEntry;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.SoilResistance;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.soilResistance.SoilResistanceEntry;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.floor.Floor;
import pl.koneckimarcin.electricalprotocolsmanager.structure.room.Room;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingDtoRepository;
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

    private final BuildingDtoRepository buildingDtoRepository;

    public PdfGenerator(PdfService pdfService, PdfHeadingService headingService, PdfFooterService footerService,
                        PdfMeasurementDataService measurementDataService, PdfTitlePageService titlePageService,
                        PdfLegendService legendService, PdfTheoryService theoryService,
                        PdfElectricianPageService electricianPageService, PdfStatisticPageService statisticPageService,
                        BuildingDtoRepository buildingDtoRepository) {
        this.pdfService = pdfService;
        this.headingService = headingService;
        this.footerService = footerService;
        this.measurementDataService = measurementDataService;
        this.titlePageService = titlePageService;
        this.legendService = legendService;
        this.theoryService = theoryService;
        this.electricianPageService = electricianPageService;
        this.statisticPageService = statisticPageService;
        this.buildingDtoRepository = buildingDtoRepository;
    }

    public void createPdfDocument(String directory) throws IOException {

        ////////////////////////////// temporary place for data

        MeasurementEntry measurement3 = new CircuitInsulationResistanceTnsEntry(
                1, "", "Obwod 1", 9, 1044, 1048, 909,
                975, 1079, 1002, 994, 1040, 988, 10.0f);

        MeasurementEntry measurement4 = new CircuitInsulationResistanceTnsEntry(
                2, "", "Obwod 2", 0, 0, 0, 0,
                975, 0, 0, 994, 0, 988, 10.0f);

        MeasurementEntry measurement5 = new CircuitInsulationResistanceTncEntry(3, "",
                "Obwod tnc", 10, 20, 30, 40, 50, 60, 11);

        MeasurementEntry measurement6 = new ResidualCurrentProtectionParametersEntry(3, "",
                "Tescik", "A", "[A]", 10, 11, 20, 18, 9, 10);

        MeasurementEntry measurement7 = new SoilResistanceEntry(10, "Dupa", "Kupa",
                9, 70);

        MeasurementEntry mesurement8 = new ContinuityOfSmallResistanceEntry(10, "",
                "Dupa", Continuity.PRESERVED, 9.0f, 10.2f);

        SoilResistance soil1 = new SoilResistance();
        soil1.setMeasurementEntries(List.of(measurement7));

        ContinuityOfSmallResistance continuity = new ContinuityOfSmallResistance();
        continuity.setMeasurementEntries(List.of(mesurement8));

        CircuitInsulationResistanceTns insulation1 = new CircuitInsulationResistanceTns(500);
        insulation1.setMeasurementEntries(List.of(measurement3, measurement4));

        CircuitInsulationResistanceTnc insulation2 = new CircuitInsulationResistanceTnc(500);
        insulation2.setMeasurementEntries(List.of(measurement5));

        ResidualCurrentProtectionParameters residual1 = new ResidualCurrentProtectionParameters();
        residual1.setMeasurementEntries(List.of(measurement6));

        ProtectionMeasurementEntry measurement1 =
                new ProtectionMeasurementEntry(1, "", 230, "Pokoj 1 GN 1",
                        "S301", 'B', 10.0f, 0.37f);

        ProtectionMeasurementEntry measurement2 =
                new ProtectionMeasurementEntry(2, "", 230, "Pokoj 1 GN 2",
                        "S301", 'D', 10.0f, 3f);

        ProtectionAgainstElectricShockByAutomaticShutdown protection =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f, NetworkType.TNS);
        protection.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection2 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f, NetworkType.TNS);
        protection2.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection3 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f, NetworkType.TNS);
        protection3.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection4 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f, NetworkType.TNS);
        protection4.setMeasurementEntries(List.of(measurement1, measurement2));

        ProtectionAgainstElectricShockByAutomaticShutdown protection5 =
                new ProtectionAgainstElectricShockByAutomaticShutdown(230,
                        50, 1.0f, 0.20f, NetworkType.TNS);
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
        Room test = new Room("Test");
        Room bed = new Room("Sypialnia");
        Room cloth = new Room("Garderoba");

        downFloor.addRoom(bath);
        downFloor.addRoom(test);
        test.addMeasurementMain(insulation2);
        test.addMeasurementMain(residual1);
        test.addMeasurementMain(soil1);
        bath.addMeasurementMain(protection);
        bath.addMeasurementMain(continuity);
        downFloor.addRoom(salon);
        salon.addMeasurementMain(protection2);
        salon.addMeasurementMain(protection3);
        upperFloor.addRoom(bed);
        bed.addMeasurementMain(protection4);
        bed.addMeasurementMain(insulation1);
        upperFloor.addRoom(cloth);
        cloth.addMeasurementMain(protection5);

        /////////////////////////////// temporary place for data

        PDDocument doc = new PDDocument();
        //count pages for rooms measurements
        int pagesCountMeasurements = pdfService.calculateNumberOfMeasurementsPages(buildingTest);
        //count pages for theory
        int pagesCountTheory = pdfService.calculateNumberOfTheoryPages(buildingTest);
        //add pages for title, theory...
        File file = new File(directory);

        //add title page
        pdfService.addPages(doc, 1);
        titlePageService.addTitlePage(doc, titlePageData);
        //add measurements
        pdfService.addPages(doc, pagesCountMeasurements);
        measurementDataService.addMeasurementDataTable(doc, buildingTest, pagesCountMeasurements);
        //add legend page/pages
        pdfService.addPages(doc, 1);
        legendService.addLegendData(doc, doc.getNumberOfPages() - 1, buildingTest.getMeasurementMainList());
        //add theory page/pages
        pdfService.addPages(doc, pagesCountTheory);
        theoryService.addTheory(doc, doc.getNumberOfPages(), pagesCountTheory, buildingTest);
        //add electricians page
        pdfService.addPages(doc, 1);
        electricianPageService.addData(doc, List.of(electrician, electrician2), doc.getNumberOfPages() - 1);
        //add statistic page
        pdfService.addPages(doc, 1);
        statisticPageService.addStatisticDate(doc, buildingTest, doc.getNumberOfPages() - 1);
        //add headers
        headingService.addHeading(doc, titlePageData.getHeadingTextData());
        //add footers
        footerService.addFooter(doc, titlePageData.getDocumentNumber());

        doc.save(file);
        doc.close();
    }
}
