package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.*;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.*;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.storage.PdfRepository;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;

import java.io.IOException;

@Service
public class PdfGenerator {

    @Autowired
    private PdfService pdfService;
    @Autowired
    private PdfHeadingComponent headingService;
    @Autowired
    private PdfFooterComponent footerService;
    @Autowired
    private PdfMeasurementDataComponent measurementDataService;
    @Autowired
    private PdfTitlePageService titlePageService;
    @Autowired
    private PdfLegendComponent legendService;
    @Autowired
    private PdfTheoryService theoryService;
    @Autowired
    private PdfElectricianPageComponent electricianPageService;
    @Autowired
    private PdfStatisticPageComponent statisticPageService;
    @Autowired
    private PdfRepository pdfRepository;
    @Autowired
    private MainPdfService mainPdfService;
    @Autowired
    private BuildingRepository buildingRepository;


    public void createPdfDocument(Building building, PdfTitlePage titlePageData) throws IOException {

        PDDocument doc = new PDDocument();

        Font font = new Font(doc);

        //count pages for rooms measurements
        int pagesCountMeasurements = pdfService.calculateNumberOfMeasurementsPages(building);
        //count pages for theory
        int pagesCountTheory = pdfService.calculateNumberOfTheoryPages(building);
        //add pages for title, theory...

        //add title page
        pdfService.addPages(doc, 1);
        titlePageService.addTitlePage(doc, titlePageData, font);
        //add measurements
        pdfService.addPages(doc, pagesCountMeasurements);
        measurementDataService.appendMeasurementsDataToPages(doc, building, pagesCountMeasurements, font);
        //add legend page/pages
        pdfService.addPages(doc, 1);
        legendService.appendLegendPage(doc, doc.getNumberOfPages() - 1, building.getMeasurementMainList(), font);
        //add theory page/pages
        pdfService.addPages(doc, pagesCountTheory);
        theoryService.appendTheoryPages(doc, doc.getNumberOfPages(), pagesCountTheory, building);
        //add electricians page
        pdfService.addPages(doc, 1);
        electricianPageService.appendElectriciansPage(doc, titlePageData.getElectricians(), doc.getNumberOfPages() - 1, font);
        //add statistic page
        pdfService.addPages(doc, 1);
        statisticPageService.appendStatisticsPage(doc, building, doc.getNumberOfPages() - 1, font);
        //add headers
        headingService.addHeadingToPages(doc, titlePageData.getHeadingTextData(), font);
        //add footers
        footerService.addFooterToPages(doc, titlePageData.getDocumentNumber(), font);

        mainPdfService.saveFileToDb(doc);
        doc.close();
    }
}
