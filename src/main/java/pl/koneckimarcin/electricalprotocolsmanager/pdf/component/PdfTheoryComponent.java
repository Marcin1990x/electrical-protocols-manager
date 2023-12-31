package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.main.MeasurementMain;
import pl.koneckimarcin.electricalprotocolsmanager.structure.building.Building;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfTheoryComponent {

    public void appendTheoryPages(PDDocument document, int startPage, int theoryPagesQuantity, Building building) {

        PDPageContentStream content;

        List<String> theoryImageDirectoryList = getTheoryImagesDirectories(building.getMeasurementMainList());

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

    private List<String> getTheoryImagesDirectories(List<MeasurementMain> measurementMainList) {

        List<String> addedDirectories = new ArrayList<>();

        for(MeasurementMain measurement : measurementMainList) {
            if(isNotAddedAndIsNotNull(measurement, addedDirectories))
                addedDirectories.addAll(measurement.getMeasureTheoryDirectory());
        }
        return addedDirectories;
    }
    private boolean isNotAddedAndIsNotNull(MeasurementMain measurementToAdd, List<String> addedDirectories) {
        return measurementToAdd.getMeasureTheoryDirectory() != null
                && addedDirectories.stream().noneMatch(name -> name.equals(measurementToAdd.getMeasureTheoryDirectory().get(0)));
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
