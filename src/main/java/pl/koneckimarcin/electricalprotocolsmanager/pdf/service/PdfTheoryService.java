package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfTheoryService {

    private final String dir = "src/main/resources/theoryImages/";

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

            content.drawImage(image, 30, 730 - image.getHeight());
            content.close();
        }
    }

    private List<String> getTheoryImagesDirList(List<String> measurementsNames) {

        List<String> directories = new ArrayList<>();

        boolean circuitFlag = false;

        for(String name : measurementsNames) {
            if(name.equals(TextData.measurementsMainNames.get(0))){
                directories.add(dir + "protect1.jpg");
                directories.add(dir + "protect2.jpg");
            } else if(name.equals(TextData.measurementsMainNames.get(1)) ||
                    name.equals(TextData.measurementsMainNames.get(2))) {
                if(!circuitFlag) directories.add(dir + "insulation.jpg");
                circuitFlag = true;
            } else if(name.equals(TextData.measurementsMainNames.get(3))) {
                directories.add(dir + "residual.jpg");
            } else if(name.equals(TextData.measurementsMainNames.get(4))) {
            } else if(name.equals(TextData.measurementsMainNames.get(5))) {
                directories.add(dir + "continuity.jpg");
            }else {
                throw new IllegalArgumentException("No theory image directory for this measurement name.");
            }
        }
        return directories;
    }
}
