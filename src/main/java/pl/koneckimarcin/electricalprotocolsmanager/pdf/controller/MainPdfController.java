package pl.koneckimarcin.electricalprotocolsmanager.pdf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.PdfGenerator;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.BuildingRepository;

import java.io.IOException;

@RestController
public class MainPdfController {

    private PdfGenerator pdfGenerator;
    private BuildingRepository buildingDtoRepository;

    private Building building;

    public MainPdfController(PdfGenerator pdfGenerator, BuildingRepository buildingDtoRepository) {
        this.pdfGenerator = pdfGenerator;
        this.buildingDtoRepository = buildingDtoRepository;
    }

    @GetMapping("/test")
    public Building test(){

        return building = buildingDtoRepository.findById(1).get();
    }

    @GetMapping("/")
    public void run() throws IOException {

        pdfGenerator.createPdfDocument("F:\\Programista\\Pomiary elektryczne\\test.pdf", building);
    }
}
