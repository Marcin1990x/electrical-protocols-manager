package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePage;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.titlePage.PdfTitlePageController;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingController;

import java.io.IOException;

@RestController
public class MainPdfController {

    private PdfGenerator pdfGenerator;
    private BuildingController buildingController;
    private PdfTitlePageController pdfTitlePageController;

    private Building building;
    private PdfTitlePage titlePageData;

    public MainPdfController(PdfGenerator pdfGenerator, BuildingController buildingController,
                             PdfTitlePageController pdfTitlePageController) {
        this.pdfGenerator = pdfGenerator;
        this.buildingController = buildingController;
        this.pdfTitlePageController = pdfTitlePageController;
    }

    @GetMapping("/getData")
    public void getData() {

        building = buildingController.getBuildings().stream().findFirst().get();
        titlePageData = pdfTitlePageController.getTitlePage();
    } // taki sam dla pdftitlepage

    @GetMapping("/createPdf")
    public void run() throws IOException {

        pdfGenerator.createPdfDocument("F:\\Programista\\Pomiary elektryczne\\electrical-protocols-manager-ui\\electrical-protocols-manager-ui\\src\\test.pdf",
                building, titlePageData);
    }
}
