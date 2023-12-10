package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfTheoryService {

    //private final String dir = "src/main/resources/theoryImages/"; // intellij
    private final String dir = "theoryImages/"; // for jar

    public void addTheory(PDDocument document, int startPage, int pages, Building building) throws IOException {

        PDPageContentStream content;
        PDImageXObject image;

        List<String> measurementDistinctNames = building.extractMeasurementMainDistinctNames();
        List<String> theoryImagesDirectories = getTheoryImagesDirList(measurementDistinctNames);

        int theoryImage = 0;

        for (int i = startPage - pages; i < startPage; i++) {

            content = new PDPageContentStream(document, document.getPage(i),
                    PDPageContentStream.AppendMode.APPEND, false);
            image = PDImageXObject.createFromFile(theoryImagesDirectories.get(theoryImage), document);
            theoryImage++;

            int div = image.getWidth() / 500;
            int yPos = image.getHeight()/div;

            content.drawImage(image, 50, 730 - yPos, 500, image.getHeight()/div);
            content.close();
        }
    }

    private List<String> getTheoryImagesDirList(List<String> measurementsNames) {

        List<String> directories = new ArrayList<>();

        boolean circuitFlag = false;

        for (String name : measurementsNames) {
            if (name.equals(TextsPL.measurementsMainNames.get(0))) {
                directories.add(dir + "protect1.jpg");
                directories.add(dir + "protect2.jpg");
            } else if (name.equals(TextsPL.measurementsMainNames.get(1)) ||
                    name.equals(TextsPL.measurementsMainNames.get(2))) {
                if (!circuitFlag) directories.add(dir + "insulation.jpg");
                circuitFlag = true;
            } else if (name.equals(TextsPL.measurementsMainNames.get(3))) {
                directories.add(dir + "residual.jpg");
            } else if (name.equals(TextsPL.measurementsMainNames.get(4))) {
            } else if (name.equals(TextsPL.measurementsMainNames.get(5))) {
                directories.add(dir + "continuity.jpg");
            } else {
                throw new IllegalArgumentException("No theory image directory for this measurement name.");
            }
        }
        return directories;
    }
}
