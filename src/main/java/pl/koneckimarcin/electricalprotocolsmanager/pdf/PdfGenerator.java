package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfInstallation;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfMeasurement;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.TypeOfWeather;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.model.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.*;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;
import pl.koneckimarcin.electricalprotocolsmanager.utilities.electrician.Electrician;

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

    private final BuildingRepository buildingDtoRepository;

    public PdfGenerator(PdfService pdfService, PdfHeadingService headingService, PdfFooterService footerService,
                        PdfMeasurementDataService measurementDataService, PdfTitlePageService titlePageService,
                        PdfLegendService legendService, PdfTheoryService theoryService,
                        PdfElectricianPageService electricianPageService, PdfStatisticPageService statisticPageService,
                        BuildingRepository buildingDtoRepository) {
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

    public void createPdfDocument(String directory, Building building) throws IOException {

//        ElectricianDto electrician = new ElectricianDto(
//                "Elektryk", "Pierwszy",
//                "Przykladowy adres 1",
//                List.of("E/244/10/20", "E/244/08/10"),
//                Position.CHECKER);
//        ElectricianDto electrician2 = new ElectricianDto(
//                "Elektryk", "Drugi",
//                "Przykladowy adres 100",
//                List.of("E/244/10/22", "E/244/90/10"),
//                Position.MEASURER);


        PdfTitlePage titlePageData = new PdfTitlePage(
                List.of(new Electrician()),
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

        PDDocument doc = new PDDocument();
        //count pages for rooms measurements
        int pagesCountMeasurements = pdfService.calculateNumberOfMeasurementsPages(building);
        //count pages for theory
        int pagesCountTheory = pdfService.calculateNumberOfTheoryPages(building);
        //add pages for title, theory...
        File file = new File(directory);

        //add title page
        pdfService.addPages(doc, 1);
        titlePageService.addTitlePage(doc, titlePageData);
        //add measurements
        pdfService.addPages(doc, pagesCountMeasurements);
        measurementDataService.addMeasurementDataTable(doc, building, pagesCountMeasurements);
        //add legend page/pages
        pdfService.addPages(doc, 1);
        legendService.addLegendData(doc, doc.getNumberOfPages() - 1, building.getMeasurementMainList());
        //add theory page/pages
        pdfService.addPages(doc, pagesCountTheory);
        theoryService.addTheory(doc, doc.getNumberOfPages(), pagesCountTheory, building);
        //add electricians page
        pdfService.addPages(doc, 1);
        electricianPageService.addData(doc, List.of(new Electrician()), doc.getNumberOfPages() - 1);
        //add statistic page
        pdfService.addPages(doc, 1);
        statisticPageService.addStatisticDate(doc, building, doc.getNumberOfPages() - 1);
        //add headers
        headingService.addHeading(doc, titlePageData.getHeadingTextData());
        //add footers
        footerService.addFooter(doc, titlePageData.getDocumentNumber());

        doc.save(file);
        doc.close();
    }
}
