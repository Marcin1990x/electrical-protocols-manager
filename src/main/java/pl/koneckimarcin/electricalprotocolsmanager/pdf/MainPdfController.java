package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageController;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingService;

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

    private final String pdfPath = "F:\\Programista\\Pomiary elektryczne\\electrical-protocols-manager-ui\\electrical-protocols-manager-ui\\src\\test.pdf";
    //private final String pdfPath = "draft.pdf";

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
    public String run() throws IOException {

        String path = pdfGenerator.createPdfDocument(pdfPath, building, titlePageData);

        return path;
    }

    @GetMapping("/copyPdf/{fileName}")
    public void copyPdf(@PathVariable String fileName) {

        pdfService.copyFile(fileName, pdfPath);
    }
}
