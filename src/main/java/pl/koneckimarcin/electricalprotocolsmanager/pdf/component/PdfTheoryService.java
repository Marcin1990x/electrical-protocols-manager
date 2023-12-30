package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfTheoryService {

    private final String theoryImagesDirectory = "theoryImages/";

    public void appendTheoryPages(PDDocument document, int startPage, int theoryPagesQuantity, Building building) {

        PDPageContentStream content;

        List<String> measurementDistinctNames = building.extractMeasurementMainDistinctNames();
        List<String> theoryImageDirectoryList = getTheoryImagesDirectoryList(measurementDistinctNames);

        int theoryImageCount = 0;

        for (int page = startPage - theoryPagesQuantity; page < startPage; page++) {

            try {
                content = new PDPageContentStream(document, document.getPage(page),
                        PDPageContentStream.AppendMode.APPEND, false);

                addImageToPage(content, document, theoryImageDirectoryList, theoryImageCount);
                theoryImageCount++;

                content.close();
            } catch (IOException e) {
                System.out.println("Error when appending image to page. " + e.getMessage());
            }
        }
    }

    private List<String> getTheoryImagesDirectoryList(List<String> measurementsNames) {

        List<String> directories = new ArrayList<>();

        boolean isTheoryForCircuitInsulationAdded = false;

        for (String name : measurementsNames) {
            if (name.equals(TextsPL.measurementsMainNames.get(0))) {
                directories.addAll(List.of(theoryImagesDirectory + "protect1.jpg", theoryImagesDirectory + "protect2.jpg"));
            } else if (name.equals(TextsPL.measurementsMainNames.get(1)) ||
                    name.equals(TextsPL.measurementsMainNames.get(2))) {
                if (!isTheoryForCircuitInsulationAdded) directories.add(theoryImagesDirectory + "insulation.jpg");
                isTheoryForCircuitInsulationAdded = true;
            } else if (name.equals(TextsPL.measurementsMainNames.get(3))) {
                directories.add(theoryImagesDirectory + "residual.jpg");
            } else if (name.equals(TextsPL.measurementsMainNames.get(4))) {
            } else if (name.equals(TextsPL.measurementsMainNames.get(5))) {
                directories.add(theoryImagesDirectory + "continuity.jpg");
            } else {
                throw new IllegalArgumentException("No theory image directory for this measurement name.");
            }
        }
        return directories;
    }
    private void addImageToPage(PDPageContentStream content, PDDocument document, List<String> imageDirectories,
                                int theoryImageCount) {

        PDImageXObject image = null;

        try {
            image = PDImageXObject.createFromFile(imageDirectories.get(theoryImageCount), document);
        } catch(IOException e) {
            System.out.println("Error when reading image from directory: " + imageDirectories.get(theoryImageCount)
                    + " " + e.getMessage());
        }
        drawImage(content, image);
    }

    private void drawImage(PDPageContentStream content, PDImageXObject image) {
        int div = image.getWidth() / 500;
        int yPosFromTop = image.getHeight() / div;
        int imageXPos = 50;
        int imageYPos = 730 - yPosFromTop;
        int imageWidth = 500;
        int imageHeight = yPosFromTop;

        try {
            content.drawImage(image, imageXPos, imageYPos, imageWidth, imageHeight);
        } catch (IOException e) {
            System.out.println("Error when adding image to page. " + e.getMessage());
        }
    }
}
