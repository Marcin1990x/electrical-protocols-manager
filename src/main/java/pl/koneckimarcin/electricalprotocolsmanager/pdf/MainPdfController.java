package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageController;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.buildingstructure.building.BuildingService;

import java.io.IOException;

@RestController
public class MainPdfController {

    @Autowired
    private PdfGenerator pdfGenerator;
    @Autowired
    private PdfTitlePageController pdfTitlePageController;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private MainPdfService pdfService;

    private Building building;
    private PdfTitlePage titlePageData;

    @GetMapping("/getData/{projectName}")
    public String getData(@PathVariable String projectName) {

        building = buildingService.retrieveByProjectName(projectName);
        titlePageData = pdfTitlePageController.getTitlePage();

        if (building == null) {
            return "Error 110. No structure.";
        } else if (titlePageData == null) {
            return "Error 111. No title page data.";
        } else if (titlePageData.getElectricians().size() == 0) {
            return "Error 112. No electricians added.";
        } else return "OK.";
    }

    @GetMapping("/createPdf")
    public void run() throws IOException {

        pdfGenerator.createPdfDocument(building, titlePageData);
    }

    @GetMapping("/saveToFile/{fileName}")
    public void saveToFile(@PathVariable String fileName) {

        pdfService.saveToFile(fileName);
    }
}
