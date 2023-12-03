package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.*;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;

import java.io.File;
import java.io.IOException;

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

    public String createPdfDocument(String directory, Building building, PdfTitlePage titlePageData) throws IOException {

        PDDocument doc = new PDDocument();

        Font font = new Font(doc);

        //count pages for rooms measurements
        int pagesCountMeasurements = pdfService.calculateNumberOfMeasurementsPages(building);
        //count pages for theory
        int pagesCountTheory = pdfService.calculateNumberOfTheoryPages(building);
        //add pages for title, theory...
        File file = new File(directory);

        //add title page
        pdfService.addPages(doc, 1);
        titlePageService.addTitlePage(doc, titlePageData, font);
        //add measurements
        pdfService.addPages(doc, pagesCountMeasurements);
        measurementDataService.addMeasurementDataTable(doc, building, pagesCountMeasurements, font);
        //add legend page/pages
        pdfService.addPages(doc, 1);
        legendService.addLegendData(doc, doc.getNumberOfPages() - 1, building.getMeasurementMainList(), font);
        //add theory page/pages
        pdfService.addPages(doc, pagesCountTheory);
        theoryService.addTheory(doc, doc.getNumberOfPages(), pagesCountTheory, building);
        //add electricians page
        pdfService.addPages(doc, 1);
        electricianPageService.addData(doc, titlePageData.getElectricians(), doc.getNumberOfPages() - 1, font);
        //add statistic page
        pdfService.addPages(doc, 1);
        statisticPageService.addStatisticDate(doc, building, doc.getNumberOfPages() - 1, font);
        //add headers
        headingService.addHeading(doc, titlePageData.getHeadingTextData(), font);
        //add footers
        footerService.addFooter(doc, titlePageData.getDocumentNumber(), font);

        doc.save(file);
        doc.close();

        return file.getAbsolutePath();
    }
}
